package com.example.backendmp3app.formatter;

import com.example.backendmp3app.model.Avatar;
import com.example.backendmp3app.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AvatarFormatter implements Formatter<Avatar> {

    private AvatarService avatarService;

    @Autowired
    public AvatarFormatter(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @Override
    public Avatar parse(String text, Locale locale) throws ParseException {
        return avatarService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Avatar object, Locale locale) {
        return null;
    }
}
