package br.com.gedielsonvieira.todolist.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    //Processo:é passado no source o corpo da requisição contendo o atributo que deve ser alterado sendo o objeto
    //taskmodel com isso é verificado quais são os atributos nulos e estes são ignorados
    //Mesclar propriedades não nulas de dois objetos.
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    //Pega todos atributos nulos
    public static String[] getNullPropertyNames(Object source) {
        //acessa as informações de um objeto
        final BeanWrapper src = new BeanWrapperImpl(source);

        //atributos
        PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : propertyDescriptors) {
            Object propertyValue = src.getPropertyValue(pd.getName());
            if (propertyValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
