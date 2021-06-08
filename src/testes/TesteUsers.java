package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controllers.UserController;
import views.ChatForm;
import views.MainForm;

class TesteUsers {

	@Test
	void testeUser() {
		 new UserController("Iaan Mesquita", 201904219, "Ciências da comp");
		 assertEquals(UserController.getUserInstance().getNome(),"Iaan Mesquita");
		 assertEquals(UserController.getUserInstance().getMatricula(),201904219);
		 assertEquals(UserController.getUserInstance().getCurso(),"Ciências da comp");
	}
	
	@Test
	void testeLogin() {
		 new UserController("Iaan Mesquita", 201904219, "Ciências da comp");
		 new MainForm();
		 assertFalse(ChatForm.isOpened);
		 assertNotNull(UserController.getInstance());
	}
	
	//Executar teste somente com o servidor aberto.
	@Test
	void testeConectadoServidor() {
		 new UserController("Iaan Mesquita", 201904219, "Ciências da comp");
		 new MainForm();
		 new ChatForm();
		 UserController.conectarUser();		 
		 assertNotNull(UserController.soc);
		
	}
	
}
