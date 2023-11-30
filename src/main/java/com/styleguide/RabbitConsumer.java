package com.styleguide;

import com.styleguide.models.Color;
import com.styleguide.models.Piece;
import com.styleguide.models.PieceColor;
import com.styleguide.repositories.PieceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitConsumer {

    private final PieceRepository pieceRepository;

    @Autowired
    public RabbitConsumer(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    @RabbitListener(queues = "colors")
    public void consumeColorResponse(@Payload PieceColor response){
        Piece piece = pieceRepository.findById(response.id()).orElseThrow();

        piece.setPrimaryColor(response.primaryColor());
        piece.setSecondaryColor(response.secondaryColor());

        List<Integer> averageColor = response.averageColor();
        piece.setAverageColor(averageColor.get(0).toString() + "," + averageColor.get(1).toString() + "," + averageColor.get(2).toString());

        pieceRepository.save(piece);
    }

}
