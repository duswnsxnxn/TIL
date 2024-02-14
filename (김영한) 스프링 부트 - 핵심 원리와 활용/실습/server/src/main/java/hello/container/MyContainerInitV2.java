package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
@HandlesTypes({AppInit.class})
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("set = " + set + ", servletContext = " + servletContext);

        for (Class<?> aClass : set) {
            try {
                AppInit appInit = (AppInit) aClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(servletContext);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
