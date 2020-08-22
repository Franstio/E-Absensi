/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08.Libs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import NullProgrammers_08.Models.Imodels;
import NullProgrammers_08.Models.ModelsBase;
import NullProgrammers_08.Models.RolesModel;
import NullProgrammers_08.Models.UsersModel;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javafx.util.Pair;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DRONE003
 */
public class utilLib {
    public static enum Action{
        CREATE,
        DELETE,
        UPDATE
    }
    public static enum Status{
        Hadir,
        Alpha,
        Sakit,
        Izin
    }
    public static String hash(String text) 
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] byt = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            BigInteger big = new BigInteger(1,byt);
            StringBuilder sb = new StringBuilder(big.toString(16));
            while (sb.length() < 32)
            {
                sb.insert(0, '0');
            }
            return sb.toString();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }
    public static <T extends ModelsBase> String generateCode(String prefix,String table,String key,Class<T> classT)
    {
        List<Imodels> models = sqlLib.searchData4(table, new String[]{key}, "order by " + key + " desc LIMIT 1",classT);
        int id=0;
        if (models != null && models.size() > 0)
        {
            id = Integer.parseInt(models.get(0).loadValues(key).toString().substring(prefix.length()));
        }
        id = id + 1;
        return String.format("%s%04d", prefix,id);
    }
    public static Pair<RolesModel,UsersModel> findRole(UsersModel[] users)
    {
        List<Imodels> data = sqlLib.searchData4("roles", new String[]{"*"}, "",RolesModel.class);
        List<RolesModel> roles = new ArrayList<RolesModel>();
        for (int i=0;i<data.size();i++)
        {
            System.out.println("ROLES: "+String.join(",", data.get(i).loadAllValues()));
            roles.add(data.get(i).loadClass(new RolesModel()));
            System.out.println(data.get(i).loadClass(new RolesModel()).id);
        }
        for (int i=0;i<users.length;i++)
        {
            System.out.println("NULL");
            for (int j=0;j<roles.size();j++)
            {
                System.out.println("TTTTT" + users[i].id_role);
                System.out.println("TTTTT" + users[i].name);
                System.out.println(users[i].id_role  + "==" + roles.get(j).id );
                if (users[i].id_role == roles.get(j).id )
                {
                    return new Pair<RolesModel,UsersModel>(roles.get(j),users[i]);
                }
            }
        }
        System.out.println("ER");
        return null;
    }
    public static DefaultTableModel loadTable(List<Imodels> models)
    {
        DefaultTableModel table = new DefaultTableModel();
        String[] colNames = models.get(0).loadAllNames();
        for (String i : colNames)
        {
            table.addColumn(i);
        }
        for (Imodels model : models)
        {
            Object[] data = model.loadAllValues();
            for (int i=0;i<data.length;i++)
            {
                data[i] = data[i].toString().substring(1, data[i].toString().length()-1);
            }
            table.addRow(data);
        }
        return table;
    }
}
