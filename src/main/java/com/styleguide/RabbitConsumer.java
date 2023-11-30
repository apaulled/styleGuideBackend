package com.styleguide;

import com.styleguide.models.Piece;
import com.styleguide.models.PieceColor;
import com.styleguide.repositories.PieceRepository;
import com.styleguide.services.PieceService;
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
    public void consumeGoogleFetch(@Payload PieceColor response){
        Piece piece = pieceRepository.findById(response.id()).orElseThrow();
        List<Integer> color = response.color();
        piece.setPrimaryColor("(" + color.get(0).toString() + "," + color.get(1).toString() + "," + color.get(2).toString() + ")");
        pieceRepository.save(piece);
    }

}
