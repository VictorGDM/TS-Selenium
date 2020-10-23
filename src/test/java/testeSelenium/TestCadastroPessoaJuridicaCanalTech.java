package testeSelenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestCadastroPessoaJuridicaCanalTech {

	static WebDriver driver;

	void setUserJuridico(String nome, String dataNascimento, String cpf, String telefone, String confirmacaoTelefone,
			String email, String confirmacaoEmail, String senha, String confirmacaoSenha) {
		driver.findElement(By.id("pf_nome_cliente")).sendKeys(nome);
		driver.findElement(By.id("pf_data_nascimento")).sendKeys(dataNascimento);
		driver.findElement(By.id("pf_cpf_cliente")).sendKeys(cpf);
		driver.findElement(By.id("telefone_cliente")).sendKeys(telefone);
		driver.findElement(By.id("telefone_cliente_2")).sendKeys(confirmacaoTelefone);
		driver.findElement(By.id("email_cliente")).sendKeys(email);
		driver.findElement(By.id("email_cliente2")).sendKeys(confirmacaoEmail);
		driver.findElement(By.id("senha_cliente")).sendKeys(senha);
		driver.findElement(By.id("senha_cliente2")).sendKeys(confirmacaoSenha);
	}

	void clearUserJuridico() {
		driver.findElement(By.id("pf_nome_cliente")).clear();
		driver.findElement(By.id("pf_data_nascimento")).clear();
		driver.findElement(By.id("pf_cpf_cliente")).clear();
		driver.findElement(By.id("telefone_cliente")).clear();
		driver.findElement(By.id("telefone_cliente_2")).clear();
		driver.findElement(By.id("email_cliente")).clear();
		driver.findElement(By.id("email_cliente2")).clear();
		driver.findElement(By.id("senha_cliente")).clear();
		driver.findElement(By.id("senha_cliente2")).clear();
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get("https://loja.canaltech.com.br/cadastro");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.clearUserJuridico();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
