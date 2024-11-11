package com.alurachallenge.alurachallenge_literalura.Models;

public enum Language {
    ES("es","Spanish", "Español"),
    EN("en","English","Inglés"),
    PT("pt", "Portuguese","Portugués"),
    FR("fr", "French","Francés");

    private String languageCode;
    private String languageEnglish;
    private String languageSpanish;
    Language(String languageCode, String languageEnglish, String languageSpanish){
        this.languageCode = languageCode;
        this.languageEnglish = languageEnglish;
        this.languageSpanish = languageSpanish;
    }

    public static Language fromCode(String text){
        for (Language language: Language.values()){
            if (language.languageCode.equalsIgnoreCase(text)){
                return language;
            }
        }
        throw new IllegalArgumentException("No encontrado para el código "+text);
    }

    public static Language fromEnglish(String text){
        for (Language language: Language.values()){
            if (language.languageEnglish.equalsIgnoreCase(text)){
                return language;
            }
        }
        throw new IllegalArgumentException("No encontrado para el idioma "+text);
    }

    public static Language fromSpanish(String text){
        for (Language language: Language.values()){
            if (language.languageSpanish.equalsIgnoreCase(text)){
                return language;
            }
        }
        throw new IllegalArgumentException("No encontrado para el idioma "+text);
    }
}
