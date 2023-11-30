package com.styleguide.services;

import com.styleguide.RabbitDispatch;
import com.styleguide.models.Color;
import com.styleguide.models.ColorOutfitRequest;
import com.styleguide.models.UserCloset;
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

    public void requestColorOutfit(UUID userId, Color color) {
        UserCloset closet = pieceService.getCloset(userId);
        ColorOutfitRequest request = new ColorOutfitRequest(userId, color, closet);
        rabbitDispatch.colorOutfitRequest(request);
    }

}
