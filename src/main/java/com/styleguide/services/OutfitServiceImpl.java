package com.styleguide.services;

import com.styleguide.models.dto.ThemeOutfitRequest;
import com.styleguide.models.enums.Color;
import com.styleguide.models.dto.ColorOutfitRequest;
import com.styleguide.models.dto.UserCloset;
import com.styleguide.models.enums.Theme;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OutfitServiceImpl implements OutfitService {

    private final PieceService pieceService;
    private final RabbitDispatch rabbitDispatch;

    public OutfitServiceImpl(PieceService pieceService, RabbitDispatch rabbitDispatch) {
        this.pieceService = pieceService;
        this.rabbitDispatch = rabbitDispatch;
    }

    @Override
    public void requestColorOutfit(UUID userId, Color color) {
        UserCloset closet = pieceService.getCloset(userId);
        ColorOutfitRequest request = new ColorOutfitRequest(userId, color, closet);
        rabbitDispatch.colorOutfitRequest(request);
    }

    @Override
    public void requestThemeOutfit(UUID userId, Theme theme) {
        UserCloset closet = pieceService.getCloset(userId);
        ThemeOutfitRequest request = new ThemeOutfitRequest(userId, theme, closet);
        rabbitDispatch.themeOutfitRequest(request);
    }
}
