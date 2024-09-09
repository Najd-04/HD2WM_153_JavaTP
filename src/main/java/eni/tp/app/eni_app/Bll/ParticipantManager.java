package eni.tp.app.eni_app.Bll;

import eni.tp.app.eni_app.Bo.Participant;
import eni.tp.app.eni_app.Dao.IDAOParticipant;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipantManager implements IParticipantManager{

    private IDAOParticipant participantDAO;


    public ParticipantManager(IDAOParticipant participantDAO) {
       this.participantDAO = participantDAO;

    }
    @Override
    public List<Participant> getParticipants() {


        return participantDAO.findAll();

    }

    @Override
    public Participant getParticipant(Long id) {
        return participantDAO.findById(id);
    }
}
