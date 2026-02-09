package com.project.fitsense.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class GeminiRequestDto {
    private List<Content> contents;

    @Data
    public static class Content {
        private List<Part> parts;


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Part {
        private String text;
    }

    public static GeminiRequestDto getGeminiRequest(String prompt) {
        GeminiRequestDto.Part part = new GeminiRequestDto.Part(prompt);
        GeminiRequestDto.Content content = new GeminiRequestDto.Content();
        content.setParts(List.of(part));
        GeminiRequestDto request = new GeminiRequestDto();
        request.setContents(List.of(content));
        return request;
    }
}