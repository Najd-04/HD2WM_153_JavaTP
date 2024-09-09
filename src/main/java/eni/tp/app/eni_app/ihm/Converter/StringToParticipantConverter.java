package eni.tp.app.eni_app.ihm.Converter;



import eni.tp.app.eni_app.Bll.IParticipantManager;
import eni.tp.app.eni_app.Bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToParticipantConverter implements Converter<String, Participant> {


    @Autowired
    private IParticipantManager ParticipantManager;

    @Override
    public Participant convert(String idParticipants) {

        return ParticipantManager.getParticipant(Long.parseLong(idParticipants));
    }
}
