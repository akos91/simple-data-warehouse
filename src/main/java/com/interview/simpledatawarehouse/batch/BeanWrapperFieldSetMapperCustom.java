package com.interview.simpledatawarehouse.batch;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;

public class BeanWrapperFieldSetMapperCustom<T> extends BeanWrapperFieldSetMapper<T> {

    @Override
    protected void initBinder(DataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (!StringUtils.isEmpty(text)) {
                    setValue(LocalDate.parse(text, formatter));
                } else {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                Object date = getValue();
                if (date != null) {
                    return formatter.format((LocalDate) date);
                } else {
                    return "";
                }
            }
        });
    }
}
