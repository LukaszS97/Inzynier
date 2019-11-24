package app.controller;

import app.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailApi {

    private MailService mailService;

    @Autowired
    public MailApi(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/sendMail")
    public String sendMail() throws MessagingException {
        mailService.sendMail("maciek-skoczylas@wp.pl",
                "Rekrutacja na stanowisko junior angular developer",
                "<b>Witam</b><br>W imieniu firmy CalcioSoft mam przyjemność zaproponować Panu pracę na stanowisku junior angular developera. " +
                        "Jesteśmy firmą założoną na początku roku 2019 w Lublinie i poszukujemy młodych i ambitnych programistów do naszego zespołu, " +
                        "oferujemy płatne staże z dwumiesięcznym szkoleniem tematycznym. Wymagamy dostępności minimalnie 20 godzin tygodniowo w wybranych " +
                        "przez Pana godzinach. </b><br></b><br>" +
                        "Jeżeli jest Pan zainteresowana ofertą prosimy o kontakt pod numerem telefonu 84 639 34 79 </b><br>" +
                        "Pozdrawiam Marzena Wyderko.</b><br></b><br></b><br>" +
                        "Informujemy iż dane rekrutacyjne uzyskaliśmy od spółki eLeader z siedzibą w Lublinie. ", true);
        return "wysłano";
    }
}
