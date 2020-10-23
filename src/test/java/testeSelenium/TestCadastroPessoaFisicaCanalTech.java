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

	// @Test
	void test() {
	}

	/*
	 * Testando se é possível criar um usuário com um nome simples.
	 */
	// @Test
	void testeNomeSimples() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Victor", "12/05/2000", "72097897070", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_nome_cliente_erro")).isDisplayed());
		assertEquals("Digite o seu nome completo, por favor.",
				driver.findElement(By.id("pf_nome_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input de nome em branco.
	 */
	// @Test
	void testeNomeNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("", "12/05/2000", "72097897070", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_nome_cliente_erro")).isDisplayed());
		assertEquals("Digite o seu nome completo, por favor.",
				driver.findElement(By.id("pf_nome_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível inserir letras no input de data.
	 */
	// @Test
	void testeDataNascimentoComLetra() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "teste", "72097897070", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("", driver.findElement(By.id("pf_data_nascimento")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário com o input de data incompleta.
	 */
	// @Test
	void testeDataNascimentoIncompleta() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "0101", "72097897070", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
		assertEquals("Data de nascimento inválida.", driver.findElement(By.id("pf_data_nascimento_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input de data, com uma data que
	 * não existe.
	 */
	// @Test
	void testeDataNascimentoQueNaoExiste() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "50062000", "72097897070", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
		assertEquals("Data de nascimento inválida.", driver.findElement(By.id("pf_data_nascimento_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input de data, com uma data
	 * futura.
	 */
	// @Test
	void testeDataNascimentoFutura() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01062050", "72097897070", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
		assertEquals("Data de nascimento inválida.", driver.findElement(By.id("pf_data_nascimento_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input de data, com uma data
	 * próxima da data presente, fazendo assim com que esse usuário seja menor de
	 * idade ou até mesmo um recém-nascido.
	 */
	// @Test
	void testeDataNascimentoMenorDeIdade() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "18102020", "72097897070", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertFalse(driver.findElement(By.id("pf_data_nascimento_erro")).isDisplayed());
	}

	/*
	 * Testando se é possível criar um usuário com o input do CPF, com um CPF
	 * inválido.
	 */
	// @Test
	void testeCpfInvalido() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "00000000000", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_cpf_cliente_erro")).isDisplayed());
		assertEquals("CPF inválido! Por favor, digite um número válido.",
				driver.findElement(By.id("pf_cpf_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input do CPF em branco.
	 */
	// @Test
	void testeCpfNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pf_cpf_cliente_erro")).isDisplayed());
		assertEquals("Para prosseguir com o cadastro, por favor, informe um CPF válido.",
				driver.findElement(By.id("pf_cpf_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input do CPF contendo letras.
	 */
	// @Test
	void testeCpfComLetra() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "7490fa6190D", "8737999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("749.061.90", driver.findElement(By.id("pf_cpf_cliente")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário com o input do telefone fixo, com um
	 * número inválido.
	 */
	// @Test
	void testeTelefoneFixoInvalido() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "9999999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro")).isDisplayed());
		assertEquals("O número de telefone informado é inválido.",
				driver.findElement(By.id("telefone_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input do telefone fixo, com um
	 * número incompleto.
	 */
	// @Test
	void testeTelefoneFixoIncompleto() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "999999", "87981999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro")).isDisplayed());
		assertEquals("O telefone deve ter 10 digitos DDD + telefone!",
				driver.findElement(By.id("telefone_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com os input do telefone fixo e do
	 * celular em branco.
	 */
	// @Test
	void testeCadastroSemPassarNenhumNumeroParaContato() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "", "", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro_2")).isDisplayed());
		assertEquals("Informe pelo menos um número de telefone",
				driver.findElement(By.id("telefone_cliente_erro_2")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input do número do celular em
	 * branco.
	 */
	// @Test
	void testeTelefoneCelularNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "8737999999", "", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro_2")).isDisplayed());
		assertEquals("Informe pelo menos um número de telefone",
				driver.findElement(By.id("telefone_cliente_erro_2")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input do número do celular, com
	 * um numero inválido.
	 */
	// @Test
	void testeTelefoneCelularInvalido() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "8737999999", "99999999999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro_2")).isDisplayed());
		assertEquals("Informe pelo menos um número de telefone",
				driver.findElement(By.id("telefone_cliente_erro_2")).getText());
	}

	/*
	 * Testando se é possível criar um usuário com o input do número do celular, com
	 * um número incompleto.
	 */
	@Test
	void testeTelefoneCelularIncompleto() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "8737999999", "87981999", "ts-selenium@gmail.com",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.cssSelector(".blocoAlerta")).isDisplayed());

		String blocoDeAlerta = driver.findElement(By.cssSelector(".blocoAlerta")).getAttribute("innerText");
		String procurarPor = "Telefone Celular";

		assertTrue(blocoDeAlerta.toLowerCase().contains(procurarPor.toLowerCase()));
	}

	/*
	 * Testando se é possível criar um usuário com o input do e-mail em branco.
	 */
	// @Test
	void testeEmailNulo() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "8737999999", "87981999999", "",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("email_cliente_erro")).isDisplayed());
		assertEquals("Digite um endereço de e-mail válido, por favor.",
				driver.findElement(By.id("email_cliente_erro")).getText());
	}

	// @Test
	void testeSenhaI() {
		driver.findElement(By.id("PessoaFisica")).click();

		this.setUser("Fulano de Tal", "01012000", "72097897070", "8737999999", "87981999999", "",
				"ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.cssSelector(".blocoAlerta")).isDisplayed());

		String blocoDeAlerta = driver.findElement(By.cssSelector(".blocoAlerta")).getAttribute("innerText");
		String procurarPor = "Telefone Celular";

		assertTrue(blocoDeAlerta.toLowerCase().contains(procurarPor.toLowerCase()));
	}

//	/*
//	 * Somente o professor pode habilitar esse teste, para não criar uma conta e
//	 * depois não poder ser feito este teste novamente, pois as contas tem aspectos
//	 * unicos.
//	 */

//	@Test
//	void testeTodosOsCamposCorreto() {
//		driver.findElement(By.id("PessoaFisica")).click();
//
//		this.setUser("Fulano de Tal", "01012000", "73796018068", "8737999999", "87981999999", "testCorreto@gmail.com",
//				"testCorreto@gmail.com", "12345698", "12345698");
//
//		driver.findElement(By.cssSelector(".botao-commerce-img")).click();
//	}

}
