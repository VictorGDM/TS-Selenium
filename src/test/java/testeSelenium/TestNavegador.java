package testeSelenium;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestNavegador {

	static WebDriver driver;

	void usuario(String nome, String dataNascimento, String cpf, String telefone, String confirmacaoTelefone,
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

	void limparUsuario() {
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
		// driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get("https://loja.canaltech.com.br/cadastro");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.limparUsuario();
		// driver.findElement(By.cssSelector(".botao-commerce-img")).click();
		// assertTrue(driver.findElement(By.id("pf_nome_cliente_erro")).isDisplayed());
	}

	// @Test
	void testeNomeCompleto() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.usuario("Victor", "12/05/2000", "74900619035", "87999999999", "87999999999", "blabla@gmail.com",
				"blabla@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_nome_cliente_erro")).isDisplayed());
		assertEquals("Digite o seu nome completo, por favor.",
				driver.findElement(By.id("pf_nome_cliente_erro")).getText());
	}

	// @Test
	void testeDataNascimento() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.usuario("Fulano de Tal", "teste", "74900619035", "8737999999", "87981999999", "blabla@gmail.com",
				"blabla@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("", driver.findElement(By.id("pf_data_nascimento")).getAttribute("value"));
	}

	// @Test
	void testeCpfInvalido() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.usuario("Fulano de Tal", "01012000", "00000000000", "8737999999", "87981999999", "blabla@gmail.com",
				"blabla@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_cpf_cliente_erro")).isDisplayed());
		assertEquals("CPF inválido! Por favor, digite um número válido.",
				driver.findElement(By.id("pf_cpf_cliente_erro")).getText());
	}

	// @Test
	void testeCpfNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.usuario("Fulano de Tal", "01012000", "", "8737999999", "87981999999", "blabla@gmail.com",
				"blabla@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		// assertEquals("", driver.findElement(By.id("")).getAttribute("value"));

		assertTrue(driver.findElement(By.id("pf_cpf_cliente_erro")).isDisplayed());
		assertEquals("Para prosseguir com o cadastro, por favor, informe um CPF válido.",
				driver.findElement(By.id("pf_cpf_cliente_erro")).getText());
	}

	// @Test
	void testeCpfComLetra() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.usuario("Fulano de Tal", "01012000", "7490fa6190D", "8737999999", "87981999999", "blabla@gmail.com",
				"blabla@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("749.061.90", driver.findElement(By.id("pf_cpf_cliente")).getAttribute("value"));
	}

	@Test
	void testeTelefoneFixo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.usuario("Fulano de Tal", "01012000", "74900619035", "9999999999", "87981999999", "blabla@gmail.com",
				"blabla@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro")).isDisplayed());
		assertEquals("O número de telefone informado é inválido.",
				driver.findElement(By.id("telefone_cliente_erro")).getText());
	}

	/*
	 * Somente o professor pode habilitar esse teste, para não criar uma conta e
	 * depois não poder ser feito este teste novamente, pois as contas tem aspectos
	 * unicos.
	 */

//	@Test
//	void testeTodosOsCamposCorreto() {
//		driver.findElement(By.id("PessoaFisica")).click();
//
//		this.usuario("Fulano de Tal", "01012000", "73796018068", "8737999999", "87981999999", "testCorreto@gmail.com",
//				"testCorreto@gmail.com", "12345698", "12345698");
//
//		driver.findElement(By.cssSelector(".botao-commerce-img")).click();
//	}

}
