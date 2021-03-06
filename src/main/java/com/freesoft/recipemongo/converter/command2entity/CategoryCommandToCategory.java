package com.freesoft.recipemongo.converter.command2entity;

import com.freesoft.recipemongo.command.CategoryCommand;
import com.freesoft.recipemongo.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(String.valueOf(categoryCommand.getId()));
        category.setDescription(category.getDescription());
        return category;
    }
}
