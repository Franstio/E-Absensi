/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08.Libs;
import NullProgrammers_08.Models.Imodels;
import NullProgrammers_08.Models.ModelsBase;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
/**
 *
 * @author DRONE003
 */
public class sqlLib {
    private static Connection conn = null;
    private static void getConnection()
    {
        String url = "jdbc:mysql://localhost:3306/absensi";
        String user = "root";
        String pass = "";
        try
        {
            conn = DriverManager.getConnection(url,user,pass);
        }
        catch (Exception ex)
        {
            System.err.print(ex);
        }
    }
    public static <T extends ModelsBase> List<Imodels> searchProcedure(String procedure,Class<T> model)
    {
        try
        {
            getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs= stat.executeQuery(procedure);
            System.out.println(procedure);
            List<Imodels> list = new ArrayList<Imodels>();
            String[] names= model.newInstance().loadAllNames();
            for (int i=1;i<names.length;i++)
            {
                    System.out.println("PROCEDURE: " + names[i ]);
            }
            while(rs.next())
            {                
                            
                ResultSetMetaData rsm = rs.getMetaData();
                Dictionary<String,Object> dict = new Hashtable<String,Object>();
                for (int i=0;i<names.length;i++)
                {
                    System.out.println("PROCEDURE: " + names[i ]+ " " + rs.getObject(i+1));
                    dict.put(names[i], rs.getObject(i+1));
                }
                T mod = model.newInstance().loadModels(dict).loadClass(model.newInstance());
                list.add(mod);
            }
            System.out.println(String.join(",", list.get(0).loadAllValues()));
            return list;
        }
        catch(Exception ex)
        {
            System.err.println(ex + "(PROC)");
            return null;
        }
    }
    public static <T extends ModelsBase> List<Imodels>  searchData4(String tableName,String[] col,String extend,Class<T> model)
    {

        String colname = String.join(",",col);
        String query = String.format("Select %s from %s %s;", colname,tableName,extend);
        try
        {
            getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            ResultSetMetaData rsm = null;
            List<Imodels> models = new ArrayList<Imodels>();
            System.out.println("A");
            while (rs.next())
            {
                T d = model.newInstance();
                Dictionary dict = new Hashtable();
                rsm = rs.getMetaData();
                for (int i=0;i<rsm.getColumnCount();i++)
                {
                    dict.put(rsm.getColumnName(i+1), rs.getObject(rsm.getColumnName(i+1)));
                }
                Imodels Imodel = d.loadModels(dict);
                System.out.println(String.join(",",Imodel.loadAllValues()));
                models.add(Imodel);
            }
            System.out.println("B");
            return models;
        }
        catch(Exception ex)
        {
            System.err.println(ex + " " + query);
            return null;
        }
    }
    public static <T extends ModelsBase> Imodels  searchData2(String tableName,String[] col,String extend,T model)
    {
        try
        {
            getConnection();
            String colname = String.join(",",col);
            String query = String.format("Select %s from %s %s;", colname,tableName,extend);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            ResultSetMetaData rsm = null;
            Dictionary dict = new Hashtable();
            while (rs.next())
            {
                rsm = rs.getMetaData();
                for (int i=0;i<rsm.getColumnCount();i++)
                {
                    dict.put(rsm.getColumnName(i+1), rs.getObject(rsm.getColumnName(i+1)));
                }
            }
            Imodels Imodel = model.loadModels(dict);
            return Imodel;
        }
        catch(Exception ex)
        {
            System.err.println(ex);
            return null;
        }
    }
    public static <T extends ModelsBase> HashSet<Imodels>  searchData3(String tableName,String[] col,String extend,T model)
    {
        try
        {
            getConnection();
            String colname = String.join(",",col);
            String query = String.format("Select %s from %s %s;", colname,tableName,extend);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            ResultSetMetaData rsm = null;
            Dictionary dict = new Hashtable();
            HashSet<Imodels> models = new HashSet<Imodels>();
            System.out.println("A");
            while (rs.next())
            {
                System.out.println("Z");
                rsm = rs.getMetaData();
                for (int i=0;i<rsm.getColumnCount();i++)
                {
                    dict.put(rsm.getColumnName(i+1), rs.getObject(rsm.getColumnName(i+1)));
                }
                Imodels Imodel = model.loadModels(dict);
                models.add(Imodel);
            }
            System.out.println("B");
            return models;
        }
        catch(Exception ex)
        {
            System.err.println(ex);
            return null;
        }
    }
    public static ResultSet searchData(String tableName,String[] col,String extend)
    {
        getConnection();
        String colname = String.join(",", col);
        String query = "Select " + colname + " from " + tableName + " " +extend + ";";
        try
        {
            HashSet hash = new HashSet();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            if (rs.next())
            {
                return rs;
            }
            else
            {
                return null;
            }
        }
        catch(Exception ex)
        {
            System.err.print(ex + "\n" + query);
            return null;
        }
    }
    public static void InsertData(String table,Imodels model)
    {
        String query = String.format("Insert into %s(%s) values(%s);",table,String.join(",", model.loadAllNames()),String.join(",",model.loadAllValues()) );
        try
        {
            getConnection();
            Statement stat = conn.createStatement();
            stat.execute(query);
        }
        catch (Exception ex)
        {
            System.err.println(ex + query);
        }
    }
    public static <T extends ModelsBase> Imodels getSpecificData(String destTable,Imodels model,String key,Class<T> destModel)
    {
        try
        {
            getConnection();
            String value = model.getClass().getField(key).get(model).toString();
            Statement stat = conn.createStatement();
            String query = String.format("Select * from %s where %s='%s';", destTable,key,value);
            ResultSet rs = stat.executeQuery(query);
      //      ResultSetMetaData rsm = rs.getMetaData();
            Dictionary dict = new Hashtable();
            if (rs.next())
            {
                ResultSetMetaData rsm = rs.getMetaData();
                for (int i=0;i<rsm.getColumnCount();i++)
                {
                    System.out.println(rsm.getColumnName(i+1));
                    dict.put(rsm.getColumnName(i+1), rs.getObject(i+1));
                }
                T dst = destModel.newInstance();
                dst = dst.loadModels(dict).loadClass(destModel.newInstance());
                return dst;
            }
            return null;
        }
        catch(Exception ex)
        {
            System.err.println(ex);
            return null;
        }
    }
    public static void UpdateData(String table,Imodels model,String key)
    {
        String[] col = model.loadAllNames();
        String[] data = model.loadAllValues();
        StringBuilder builder = new StringBuilder();
        Dictionary<String,String> dict = new Hashtable(); 
        builder.append(String.format("Update %s set ", table));
        for (int i=0;i<col.length;i++)
        {
            dict.put(col[i],data[i]);
        }
        Enumeration enumerate = dict.keys();
        String[] setArray = new String[dict.size()-1];
        int i=0;
        while (enumerate.hasMoreElements())
        {
            String k = enumerate.nextElement().toString();
            if (k != key)
            {
                setArray[i] = String.format("%s=%s",k,dict.get(k) );
                i=i+1;
            }
        }
        builder.append(String.format("%s where %s=%s;", String.join(",", setArray),key,dict.get(key)));
        System.out.println(builder.toString());
        getConnection();
        try
        {
            Statement stat = conn.createStatement();
            stat.execute(builder.toString());
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }
    }
    public static void DeleteData(String table,Imodels model,String key)
    {
        String value =  model.loadValues(key).toString();
        String query = String.format("Delete from %s where %s='%s';",table,key,value);
        getConnection();
        try
        {
            Statement stat = conn.createStatement();
            stat.execute(query);
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }
    }
}
