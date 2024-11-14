package com.alurachallenge.alurachallenge_literalura.Models;

public enum Language {
    ES("es"),
    EN("en"),
    PT("pt"),
    FR("fr"),
    DE("de");

    private String languageCode;
    Language(String languageCode){
        this.languageCode = languageCode;
    }

    public static Language fromCode(String text){
        for (Language language: Language.values()){
            if (language.languageCode.equalsIgnoreCase(text)){
                return language;
            }
        }
        throw new IllegalArgumentException("No encontrado para el código "+text);
    }
}
