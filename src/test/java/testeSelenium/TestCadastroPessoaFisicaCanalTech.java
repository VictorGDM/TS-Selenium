package testeSelenium;

import static org.junit.Assert.assertFalse;
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

class TestCadastroPessoaFisicaCanalTech {

	static WebDriver driver;

	void setUser(String nome, String dataNascimento, String cpf, String telefone, String confirmacaoTelefone,
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

	void clearUser() {
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
		this.clearUser();
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com um nome simples.
	 */
	@Test
	void testeNomeSimples() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano", "12/05/2000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_nome_cliente_erro")).isDisplayed());
		assertEquals("Digite o seu nome completo, por favor.",
				driver.findElement(By.id("pf_nome_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de nome em branco.
	 */
	@Test
	void testeNomeNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("", "12/05/2000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_nome_cliente_erro")).isDisplayed());
		assertEquals("Digite o seu nome completo, por favor.",
				driver.findElement(By.id("pf_nome_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel inserir letras no input de data.
	 */
	@Test
	void testeDataNascimentoComLetra() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "teste", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("", driver.findElement(By.id("pf_data_nascimento")).getAttribute("value"));
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de data incompleta.
	 */
	@Test
	void testeDataNascimentoIncompleta() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "0101", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
		assertEquals("Data de nascimento inv�lida.", driver.findElement(By.id("pf_data_nascimento_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de data, com uma data que
	 * n�o existe.
	 */
	@Test
	void testeDataNascimentoQueNaoExiste() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "50062000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
		assertEquals("Data de nascimento inv�lida.", driver.findElement(By.id("pf_data_nascimento_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de data, com uma data
	 * futura.
	 */
	@Test
	void testeDataNascimentoFutura() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01062050", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
		assertEquals("Data de nascimento inv�lida.", driver.findElement(By.id("pf_data_nascimento_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de data, com uma data
	 * pr�xima da data presente, fazendo assim com que esse usu�rio seja menor de
	 * idade ou at� mesmo um rec�m-nascido.
	 */
	@Test
	void testeDataNascimentoMenorDeIdade() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "18102020", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertFalse(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do CPF, com um CPF
	 * inv�lido.
	 */
	@Test
	void testeCpfInvalido() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "00000000000", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_cpf_cliente_erro")).isDisplayed());
		assertEquals("CPF inv�lido! Por favor, digite um n�mero v�lido.",
				driver.findElement(By.id("pf_cpf_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do CPF em branco.
	 */
	@Test
	void testeCpfNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_cpf_cliente_erro")).isDisplayed());
		assertEquals("Para prosseguir com o cadastro, por favor, informe um CPF v�lido.",
				driver.findElement(By.id("pf_cpf_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do CPF contendo letras.
	 */
	@Test
	void testeCpfComLetra() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "7490fa6190D", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("749.061.90", driver.findElement(By.id("pf_cpf_cliente")).getAttribute("value"));
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do telefone fixo, com um
	 * n�mero inv�lido.
	 */
	@Test
	void testeTelefoneFixoInvalido() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "9999999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro")).isDisplayed());
		assertEquals("O n�mero de telefone informado � inv�lido.",
				driver.findElement(By.id("telefone_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do telefone fixo, com um
	 * n�mero incompleto.
	 */
	@Test
	void testeTelefoneFixoIncompleto() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro")).isDisplayed());
		assertEquals("O telefone deve ter 10 digitos DDD + telefone!",
				driver.findElement(By.id("telefone_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com os input do telefone fixo e do
	 * celular em branco.
	 */
	@Test
	void testeCadastroSemPassarNenhumNumeroParaContato() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "", "", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro_2")).isDisplayed());
		assertEquals("Informe pelo menos um n�mero de telefone",
				driver.findElement(By.id("telefone_cliente_erro_2")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do n�mero do celular em
	 * branco.
	 */
	@Test
	void testeTelefoneCelularNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro_2")).isDisplayed());
		assertEquals("Informe pelo menos um n�mero de telefone",
				driver.findElement(By.id("telefone_cliente_erro_2")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do n�mero do celular, com
	 * um numero inv�lido.
	 */
	@Test
	void testeTelefoneCelularInvalido() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "99999999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro_2")).isDisplayed());
		assertEquals("Informe pelo menos um n�mero de telefone",
				driver.findElement(By.id("telefone_cliente_erro_2")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do n�mero do celular, com
	 * um n�mero incompleto.
	 */
	@Test
	void testeTelefoneCelularIncompleto() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345689");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("87981999", driver.findElement(By.id("telefone_cliente_2")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do e-mail em branco.
	 */
	@Test
	void testeEmailNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("email_cliente_erro")).isDisplayed());
		assertEquals("Digite um endere�o de e-mail v�lido, por favor.",
				driver.findElement(By.id("email_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input do e-mail em branco.
	 */
	@Test
	void testeEmailsDiferentes() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-seleniu@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("email_cliente2_erro")).isDisplayed());
		assertEquals("A confirma��o de e-mail est� diferente do e-mail digitado. Por favor, digite-os novamente.",
				driver.findElement(By.id("email_cliente2_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de senha em branco.
	 */
	@Test
	void testeSenhaNula() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "", "");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("senha_cliente_erro")).isDisplayed());
		assertEquals("Escolha uma senha para seu cadastro", driver.findElement(By.id("senha_cliente_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de confirma��o de senha
	 * em branco.
	 */
	@Test
	void testeSenhaDeConfirmacaoNula() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "123456798", "");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("senha_cliente2_erro")).isDisplayed());
		assertEquals("Repita a Senha", driver.findElement(By.id("senha_cliente2_erro")).getText());
	}

	/*
	 * Testando se � poss�vel criar um usu�rio com o input de confirma��o senha
	 * diferente do input de senha.
	 */
	@Test
	void testeSenhaDeConfirmacaoDiferente() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "123456798", "123456789");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("senha_cliente2_erro")).isDisplayed());
		assertEquals("Sua senha est� diferente da confirma��o. Por favor, digite-as novamente.",
				driver.findElement(By.id("senha_cliente2_erro")).getText());
	}

	/*
	 * Testando se o nivel de senha aponta baixo para uma senha simples.
	 */
	@Test
	void testeSenhaBaixa() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "123456", "123456");

		assertTrue(driver.findElement(By.id("seguranca_senha")).isDisplayed());

		String nivelSenha = driver.findElement(By.id("seguranca_senha")).getAttribute("innerText");
		String procurarPor = "Baixa";

		assertTrue(nivelSenha.toLowerCase().contains(procurarPor.toLowerCase()));
	}

	/*
	 * Testando se o nivel de senha aponta m�dio para uma senha m�dia.
	 */
	@Test
	void testeSenhaMedia() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "TMedia", "TMedia");

		assertTrue(driver.findElement(By.id("seguranca_senha")).isDisplayed());

		String nivelSenha = driver.findElement(By.id("seguranca_senha")).getAttribute("innerText");
		String procurarPor = "M�dia";

		assertTrue(nivelSenha.toLowerCase().contains(procurarPor.toLowerCase()));
	}

	/*
	 * Testando se o nivel de senha aponta alto para uma senha alta.
	 */
	@Test
	void testeSenhaAlta() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "TAlta-12", "TAlta-12");

		assertTrue(driver.findElement(By.id("seguranca_senha")).isDisplayed());

		String nivelSenha = driver.findElement(By.id("seguranca_senha")).getAttribute("innerText");
		String procurarPor = "Alta";

		assertTrue(nivelSenha.toLowerCase().contains(procurarPor.toLowerCase()));
	}

	/*
	 * Testando se o checkbox est� marcado logo quando a pagina � carregada.
	 */
	@Test
	void testeCheckboxIniciaMarcada() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "TAlta-12", "TAlta-12");

		assertTrue(driver.findElement(By.id("email")).isSelected());
	}

	/*
	 * Testando se � poss�vel desmarcar o checkbox.
	 */
	@Test
	void testeCheckboxDesmarca() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "69296495032", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "TAlta-12", "TAlta-12");

		if (driver.findElement(By.id("email")).isSelected())
			driver.findElement(By.id("email")).click();

		assertFalse(driver.findElement(By.id("email")).isSelected());
	}

//	/*
//	 * Somente o professor pode habilitar esse teste, para n�o criar uma conta e
//	 * depois n�o poder ser feito este teste novamente, pois as contas tem aspectos
//	 * unicos.
//	 */

//	@Test
//	void testeTodosOsCamposCorreto() {
//		driver.findElement(By.id("PessoaFisica")).click();
//
//		this.setUser("Fulano de Tal", "01012000", "93344225057", "8737999999", "87981999999", "testCorreto@gmail.com",
//				"testCorreto@gmail.com", "12345698", "12345698");
//
//		driver.findElement(By.cssSelector(".botao-commerce-img")).click();
//	}

}
