/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08.Models;

import java.lang.reflect.Field;
import java.util.Dictionary;
import java.util.Enumeration;
/**
 *
 * @author DRONE003
 */
public class ModelsBase implements Imodels {
    @Override
    public String[] loadAllValues() {
        Class<?> d = this.getClass();
        Field[] fields = d.getFields();
        System.out.println("FIELDS: " + fields.length);
        String[] values = new String[fields.length];
        for(int i=0;i<fields.length;i++)
        {
            try
            {
                values[i] = "'" + fields[i].get(this) + "'";
            }
            catch (Exception ex)
            {
                System.err.println(ex);
            }
        }
        return values;
    }
    @Override
    public <T extends Imodels> T loadClass(T model) {
        try
        {
            try
            {
                Class<?> a = this.getClass();
                Field[] fields = a.getFields();
                Class<?> b = model.getClass();
                Field[] fields2 = b.getFields();
                for (int i=0;i<fields.length;i++)
                {
                    fields2[i].set(model, fields[i].get(this));
                }
            }
            catch (Exception ex)
            {
                Class<?> a = this.getClass();
                Field[] fields = a.getFields();
                Class<?> b = model.getClass();
                for (int i=0;i<fields.length;i++)
                {
                    String key = fields[i].getName();
                    key = key.substring(0,1).toLowerCase() + key.substring(1);
                    String keyOri = key;
                    if (key.contains("_"))
                    {
                        String[] spl = key.split("_");
                        spl[1] = spl[1].substring(0,1).toUpperCase() + spl[1].substring(1);
                        spl[0] = spl[0].substring(0,1).toLowerCase() + spl[0].substring(1);
                        key = spl[0]+spl[1];
                    }
                    javax.persistence.Column col = b.getDeclaredField(key).getAnnotation(javax.persistence.Column.class);
                    if (col != null)
                    {
//                        System.out.println(key);
//                        System.out.println("AAA" + " " + fields[i].get(this));
                        Field field = b.getDeclaredField(key);
                        field.setAccessible(true);
                        field.set(model, fields[i].get(this));
                    }
                }
            }
            return model;
        }
        catch(Exception ex)
        {
            System.err.println(ex);
            return null;
        }
        
    }

    @Override
    public Imodels loadModels(Dictionary dict) {
        Class<?> a = this.getClass();
        Enumeration enu = dict.keys();
        while(enu.hasMoreElements())
        {
            try
            {
                String name = enu.nextElement().toString();
                System.out.println(name);
                try
                {
                    a.getField(name).set(this, a.getField(name).getType().cast(dict.get(name)));
                }
                catch(Exception ex)
                {
                    a.getField(name).set(this,dict.get(name));
                }
            }
            catch(Exception ex)
            {
                System.err.println(ex);
            }
        }
        return this;
    }

    @Override
    public String[] loadAllNames() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates
        Class<?> c = this.getClass();
        Field[] fields = c.getFields();
        String[] names = new String[fields.length];
        for (int i=0;i<fields.length;i++)
        {
            names[i] = fields[i].getName();
        }
        return names;
    }

    @Override
    public Object loadValues(String name) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try
        {
            Class<?> c = this.getClass();
            Field field = c.getField(name);
            return field.get(this);
        }
        catch (Exception ex)
        {
            System.err.println(ex);
            return null;
        }
    }
    
}
