package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Participant;

import java.util.List;

public interface IDAOParticipant {
    List<Participant> findAll();

    Participant findById(Long id);
}
