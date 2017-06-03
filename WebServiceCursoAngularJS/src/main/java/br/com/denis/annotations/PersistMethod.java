package br.com.denis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import br.com.denis.repository.Repository;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistMethod {
	List<Method> listMethods = Arrays.asList(Repository.class.getMethods());
	

}
