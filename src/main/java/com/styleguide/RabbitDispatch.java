package com.styleguide;

import com.styleguide.models.Color;
import com.styleguide.models.ColorOutfitRequest;
import com.styleguide.models.Piece;
import com.styleguide.models.PieceImageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RabbitDispatch {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitDispatch(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendImageToProcessor(Piece piece) {
        PieceImageDTO dto = piece.toDto();
        rabbitTemplate.convertAndSend("pieces", dto);
    }

    public void colorOutfitRequest(ColorOutfitRequest request) {
        rabbitTemplate.convertAndSend("outfit_requests", request);
    }

    public void dispatchTest() {
        rabbitTemplate.convertAndSend("pieces", "yuh");
    }

}
