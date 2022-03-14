package com.nc.edu.java.contract.DI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration(packages = {"com.nc.edu.java.contact.sorts", "com.nc.edu.java.contact.validator"})
public class Injector {

	//Performs an injection to fields marked with the AutoInjectable annotation
	public static <T> T inject(T object)
	{
		try{

            List<Class<?>> allClasses = new ArrayList<>();

            for(String packageName: Injector.class.getAnnotation(Configuration.class).packages()){
                allClasses.addAll(Finder.find(packageName));
            }

            List<Class> injectableClasses = new ArrayList<>();

            for (Field f: object.getClass().getDeclaredFields()){
                if(f.isAnnotationPresent(AutoInjectable.class)){
                    if(isImplementInterface(f.getType(), Collection.class)){
                        for (Class clazz: allClasses){
                            if(isImplementInterface(clazz, f.getAnnotation(AutoInjectable.class).clazz()) ||
                                    (f.getAnnotation(AutoInjectable.class).clazz().equals(clazz.getSuperclass()))){
                                injectableClasses.add(clazz);
                            }
                        }

                        List list = new ArrayList();

                        for(Class clazz: injectableClasses){
                            list.add(clazz.newInstance());
                        }
                        f.setAccessible(true);
                        f.set(object, list);
                    }
                    else{
                        for (Class clazz: allClasses){
                            if(isImplementInterface(clazz, f.getType()) ||
                                    f.getType().equals(clazz.getSuperclass())){
                                injectableClasses.add(clazz);
                            }
                        }
                        if(injectableClasses.size() == 1){
                            f.setAccessible(true);
                            f.set(object, injectableClasses.get(0).newInstance());
                        }
                        else{
                            throw new ReflectionException("No classes found or found, but more than two!");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.getMessage();
        }
        return object;

	}
	
	//Indicates whether the class implements the interface
	private static boolean isImplementInterface(Class clazz, Class implementedInterface){
        for (Class c: clazz.getInterfaces()){
            if (c.equals(implementedInterface)){
                return true;
            }
        }
        return false;
    }
}
