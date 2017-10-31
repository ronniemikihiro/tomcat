package br.com.tomcat.controller;

import br.com.tomcat.entity.Usuario;
import br.com.tomcat.enums.EnumAtivo;
import br.com.tomcat.exception.NegocioException;
import br.com.tomcat.service.UsuarioBO;
import br.com.tomcat.util.EncryptionUtil;
import br.com.tomcat.util.JSFUtil;
import br.com.tomcat.util.Menu;
import br.com.tomcat.util.StringUtil;
import org.apache.commons.mail.SimpleEmail;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ProxyConfig;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * Created by ronnie-msl on 28/09/17.
 */
@ManagedBean
@SessionScoped
public class LoginMB {

    @ManagedProperty(value = "#{usuarioBO}")
    private UsuarioBO usuarioBO;

    private Usuario usuario;
    private boolean showPanelEsqueceuSenha;
    private String emailEsqueceuSenha;
    private Menu menu;

    @PostConstruct
    private void init() {
        usuario = new Usuario();
    }

    public void login() {
        try {
            usuario.setSenha(EncryptionUtil.encrypt(usuario.getSenha()));
            usuario = usuarioBO.login(usuario);
            JSFUtil.addPropSession("usuario", usuario);
            createMenu();
            JSFUtil.goPage("index.xhtml");
            showPanelEsqueceuSenha = false;
        }catch(NegocioException e) {
            JSFUtil.addWarn(e);
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    public void logout() {
        usuario = new Usuario();
        JSFUtil.removePropSession("usuario");
        JSFUtil.goPage("login.xhtml");
        showPanelEsqueceuSenha = false;
    }

    public void sendEmailForgoteen() {
        try {
            final Usuario usuario = usuarioBO.getByEmail(emailEsqueceuSenha.trim());
            final String passwordRandom = StringUtil.getPasswordRandom();

//            new Mailer("smtp.gmail.com", 587, "ronniemikihiro@gmail.com", "matrix0139")
//                    .sendMail(new EmailBuilder()
//                    .from("Sistema Tomcat", "ronniemikihiro@gmail.com")
//                    .to(usuario.getNome(), usuario.getEmail())
//                    .subject("Envio senha provisória - Sistema Tomcat")
//                    .textHTML("<h1>Olá, " + usuario.getNome() +", sua senha provisória é: " + usuario.getSenha() + "</h1>")
//                    .build());

//            final Mailer mailer = new Mailer(
//                new ServerConfig("smtp.gmail.com", 587, "ronniemikihiro@gmail.com", "matrix0139"),
//                TransportStrategy.SMTP_TLS,
//                new ProxyConfig("proxy.goias.gov.br", 2303, "ronnie-msl", "Matrix23")
//            );
//
//            mailer.sendMail(new EmailBuilder()
//                    .from("Sistema Tomcat", "ronniemikihiro@gmail.com")
//                    .to(usuario.getNome(), usuario.getEmail())
//                    .subject("Envio senha provisória - Sistema Tomcat")
//                    .textHTML("<h1>Olá, " + usuario.getNome() +", sua senha provisória é: " + usuario.getSenha() + "</h1>")
//                    .build());

//            Properties props = new Properties();
//            props.setProperty("proxySet","true");
//            props.setProperty("http.proxyHost","proxy.goias.gov.br");
//            props.setProperty("http.proxyPort","2303");
//            System.setProperty("http.proxyUser","ronnie-msl");
//            System.setProperty("http.proxyPassword","Matrix23");
//            /** Parâmetros de conexão com servidor Gmail */
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.socketFactory.port", "465");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", "465");
//
//            Session session = Session.getDefaultInstance(props,
//                    new javax.mail.Authenticator() {
//                        protected PasswordAuthentication getPasswordAuthentication()
//                        {
//                            return new PasswordAuthentication("ronniemikihiro@gmail.com", "matrix0139");
//                        }
//                    });
//            /** Ativa Debug para sessão */
//            session.setDebug(true);
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("ronniemikihiro@gmail.com")); //Remetente
//
//            Address[] toUser = InternetAddress //Destinatário(s)
//                    .parse(usuario.getEmail());
//            message.setRecipients(Message.RecipientType.TO, toUser);
//            message.setSubject("Enviando email com JavaMail");//Assunto
//            message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
//            /**Método para enviar a mensagem criada*/
//            Transport.send(message);



            usuario.setSenha(EncryptionUtil.encrypt(passwordRandom));
            usuario.setSenhaAtiva(EnumAtivo.N);
            usuarioBO.update(usuario);

            showPanelEsqueceuSenha = false;
        }catch(NegocioException e) {
            JSFUtil.addWarn(e);
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    private void createMenu() {
        menu = new Menu(usuario);
    }

    public void setUsuarioBO(UsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isShowPanelEsqueceuSenha() {
        return showPanelEsqueceuSenha;
    }

    public void setShowPanelEsqueceuSenha(boolean showPanelEsqueceuSenha) {
        this.showPanelEsqueceuSenha = showPanelEsqueceuSenha;
    }

    public String getEmailEsqueceuSenha() {
        return emailEsqueceuSenha;
    }

    public void setEmailEsqueceuSenha(String emailEsqueceuSenha) {
        this.emailEsqueceuSenha = emailEsqueceuSenha;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
