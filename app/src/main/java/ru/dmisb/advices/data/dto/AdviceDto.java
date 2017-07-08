package ru.dmisb.advices.data.dto;

public class AdviceDto {
    private final String id;
    private final String text;

    public AdviceDto(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
