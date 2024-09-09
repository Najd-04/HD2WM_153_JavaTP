package eni.tp.app.eni_app.Bll;

import eni.tp.app.eni_app.Bo.Participant;

import java.util.List;

public interface IParticipantManager {


    List<Participant> getParticipants();

    Participant getParticipant(Long id);
}
