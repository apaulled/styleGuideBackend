package com.styleguide;

import com.styleguide.models.*;
import com.styleguide.repositories.OutfitRepository;
import com.styleguide.repositories.PieceRepository;
import com.styleguide.repositories.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitConsumer {

    private final PieceRepository pieceRepository;
    private final OutfitRepository outfitRepository;
    private final UserRepository userRepository;

    @Autowired
    public RabbitConsumer(PieceRepository pieceRepository,
                          OutfitRepository outfitRepository,
                          UserRepository userRepository) {
        this.pieceRepository = pieceRepository;
        this.outfitRepository = outfitRepository;
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = "outfit_results")
    public void consumeOutfitResponse(@Payload OutfitResult response) {
        User user = userRepository.findById(response.userId()).orElseThrow();
        Outfit outfit = new Outfit(
                user,
                pieceRepository.findById(response.headWear()).orElseThrow(),
                pieceRepository.findById(response.top()).orElseThrow(),
                pieceRepository.findById(response.bottom()).orElseThrow(),
                pieceRepository.findById(response.shoe()).orElseThrow(),
                pieceRepository.findById(response.outerWear()).orElseThrow(),
                pieceRepository.findById(response.accessory()).orElseThrow());
        outfitRepository.save(outfit);
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
