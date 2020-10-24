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

class TestCadastroPessoaJuridicaCanalTech {

	static WebDriver driver;

	void setLegalPerson(String razaoSocial, String ie, String cnpj, String nome, String dataNascimento, String cpf,
			String telefone, String confirmacaoTelefone, String email, String confirmacaoEmail, String senha,
			String confirmacaoSenha) {
		driver.findElement(By.id("razao_social")).sendKeys(razaoSocial);
		driver.findElement(By.id("ie")).sendKeys(ie);
		driver.findElement(By.id("cnpj")).sendKeys(cnpj);
		driver.findElement(By.id("pj_nome_cliente")).sendKeys(nome);
		driver.findElement(By.id("pj_data_nascimento")).sendKeys(dataNascimento);
		driver.findElement(By.id("pj_cpf_cliente")).sendKeys(cpf);
		driver.findElement(By.id("telefone_cliente")).sendKeys(telefone);
		driver.findElement(By.id("telefone_cliente_2")).sendKeys(confirmacaoTelefone);
		driver.findElement(By.id("email_cliente")).sendKeys(email);
		driver.findElement(By.id("email_cliente2")).sendKeys(confirmacaoEmail);
		driver.findElement(By.id("senha_cliente")).sendKeys(senha);
		driver.findElement(By.id("senha_cliente2")).sendKeys(confirmacaoSenha);
	}

	void clearLegalPerson() {
		driver.findElement(By.id("razao_social")).clear();
		driver.findElement(By.id("ie")).clear();
		driver.findElement(By.id("cnpj")).clear();
		driver.findElement(By.id("pj_nome_cliente")).clear();
		driver.findElement(By.id("pj_data_nascimento")).clear();
		driver.findElement(By.id("pj_cpf_cliente")).clear();
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
		this.clearLegalPerson();
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input da RS em branco.
	 */
	@Test
	void testRazaoSocialNulo() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("", "994542976", "48397112000154", "Fulano de Tal", "12/05/2000", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("razao_social_erro")).isDisplayed());
		assertEquals("Para cadastro de pessoa jurídica é necessário a Razão Social.",
				driver.findElement(By.id("razao_social_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input da IE em branco.
	 */
	@Test
	void testInscricaoEstadualNulo() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "", "48397112000154", "Fulano de Tal", "12/05/2000", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		if (driver.findElement(By.id("ie_erro")).isDisplayed()) {
			String msgErro = driver.findElement(By.id("ie_erro")).getText();
			String procurarPor1 = "Para cadastro de pessoa jurídica, preencha o campo Inscrição Estadual.";
			String procurarPor2 = "Caso sua empresa for isenta, digite ISENTO no campo.";

			assertTrue(msgErro.toLowerCase().contains(procurarPor1.toLowerCase()));
			assertTrue(msgErro.toLowerCase().contains(procurarPor2.toLowerCase()));
		}
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do CNPJ em
	 * branco.
	 */
	@Test
	void testCnpjNulo() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "", "Fulano de Tal", "12/05/2000", "72097897070", "8737999999",
				"87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("cnpj_erro")).isDisplayed());
		assertEquals("Para cadastro de pessoa jurídica é necessário o campo CNPJ. Por favor, digite um CNPJ válido.",
				driver.findElement(By.id("cnpj_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do CNPJ
	 * inválido.
	 */
	@Test
	void testCnpjInvalido() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "00000000000000", "Fulano de Tal", "12/05/2000", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("cnpj_erro")).isDisplayed());
		assertEquals("CNPJ inválido! Digite um número válido.", driver.findElement(By.id("cnpj_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do CNPJ
	 * incompleto.
	 */
	@Test
	void testCnpjIncompleto() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112", "Fulano de Tal", "12/05/2000", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("cnpj_erro")).isDisplayed());
		assertEquals("CNPJ inválido! Digite um número válido.", driver.findElement(By.id("cnpj_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do CNPJ com
	 * letras.
	 */
	@Test
	void testCnpjComLetras() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "4839sa71ci12", "Fulano de Tal", "12/05/2000", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("cnpj_erro")).isDisplayed());
		assertEquals("CNPJ inválido! Digite um número válido.", driver.findElement(By.id("cnpj_erro")).getText());

		assertEquals("48.397.112/", driver.findElement(By.id("cnpj")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de nome em
	 * branco.
	 */
	@Test
	void testeNomeNulo() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "", "12/05/2000", "72097897070", "8737999999",
				"87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pj_nome_cliente_erro")).isDisplayed());
		assertEquals("Digite o seu nome completo, por favor.",
				driver.findElement(By.id("pj_nome_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível inserir letras no input de data.
	 */
	@Test
	void testeDataNascimentoComLetra() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "teste", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("", driver.findElement(By.id("pf_data_nascimento")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de data
	 * incompleta.
	 */
	@Test
	void testeDataNascimentoIncompleta() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "0101", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("01/01", driver.findElement(By.id("pj_data_nascimento")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de data, com uma
	 * data que não existe.
	 */
	@Test
	void testeDataNascimentoQueNaoExiste() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "50062000", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("50/06/2000", driver.findElement(By.id("pj_data_nascimento")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de data, com uma
	 * data futura.
	 */
	@Test
	void testeDataNascimentoFutura() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01062050", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("01/06/2050", driver.findElement(By.id("pj_data_nascimento")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de data, com uma
	 * data próxima da data presente, fazendo assim com que esse usuário seja menor
	 * de idade ou até mesmo um recém-nascido.
	 */
	@Test
	void testeDataNascimentoMenorDeIdade() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "18102020", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("18/10/2020", driver.findElement(By.id("pj_data_nascimento")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do CPF, com um
	 * CPF inválido.
	 */
	@Test
	void testeCpfInvalido() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "00000000000",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pj_cpf_cliente_erro")).isDisplayed());
		assertEquals("CPF inválido! Por favor, digite um número válido.",
				driver.findElement(By.id("pj_cpf_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do CPF em
	 * branco.
	 */
	@Test
	void testeCpfNulo() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "", "8737999999",
				"87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pj_cpf_cliente_erro")).isDisplayed());
		assertEquals("Para prosseguir com o cadastro, por favor, informe um CPF válido.",
				driver.findElement(By.id("pj_cpf_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do CPF contendo
	 * letras.
	 */
	@Test
	void testeCpfComLetra() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "7490fa6190D",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("pj_cpf_cliente_erro")).isDisplayed());
		assertEquals("749.061.90", driver.findElement(By.id("pj_cpf_cliente")).getAttribute("value"));
		assertEquals("CPF inválido! Por favor, digite um número válido.",
				driver.findElement(By.id("pj_cpf_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do telefone
	 * fixo, com um número inválido.
	 */
	@Test
	void testeTelefoneFixoInvalido() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070",
				"9999999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("(99) 9999-9999", driver.findElement(By.id("telefone_cliente")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do telefone
	 * fixo, com um número incompleto.
	 */
	@Test
	void testeTelefoneFixoIncompleto() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070",
				"999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro")).isDisplayed());
		assertEquals("O telefone deve ter 10 digitos DDD + telefone!",
				driver.findElement(By.id("telefone_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com os input do telefone
	 * fixo e do celular em branco.
	 */
	@Test
	void testeCadastroSemPassarNenhumNumeroParaContato() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070", "",
				"", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "12345698");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("telefone_cliente_erro_2")).isDisplayed());
		assertEquals("Informe pelo menos um número de telefone",
				driver.findElement(By.id("telefone_cliente_erro_2")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do número do
	 * celular em branco.
	 */
	@Test
	void testeTelefoneCelularNulo() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070",
				"8737999999", "", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("", driver.findElement(By.id("telefone_cliente_2")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do número do
	 * celular, com um numero inválido.
	 */
	@Test
	void testeTelefoneCelularInvalido() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070",
				"8737999999", "99999999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("(99) 99999-9999", driver.findElement(By.id("telefone_cliente_2")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do número do
	 * celular, com um número incompleto.
	 */
	@Test
	void testeTelefoneCelularIncompleto() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070",
				"8737999999", "87981999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("(87) 9819-99", driver.findElement(By.id("telefone_cliente_2")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do e-mail em
	 * branco.
	 */
	@Test
	void testeEmailNulo() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070",
				"8737999999", "87981999999", "", "ts-selenium@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertEquals("", driver.findElement(By.id("email_cliente")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input do e-mail
	 * diferente do input de confirmação de e-mail.
	 */
	@Test
	void testeEmailsDiferentes() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "72097897070",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-seleniu@gmail.com", "12345698", "1234569");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		if (driver.findElement(By.id("email_cliente")).getAttribute("value") != driver
				.findElement(By.id("email_cliente2")).getAttribute("value"))
			assertEquals("ts-seleniu@gmail.com", driver.findElement(By.id("email_cliente2")).getAttribute("value"));
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de senha em
	 * branco.
	 */
	@Test
	void testeSenhaNula() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "", "");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("senha_cliente_erro")).isDisplayed());
		assertEquals("Escolha uma senha para seu cadastro", driver.findElement(By.id("senha_cliente_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de confirmação
	 * de senha em branco.
	 */
	@Test
	void testeSenhaDeConfirmacaoNula() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "123456798", "");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("senha_cliente2_erro")).isDisplayed());
		assertEquals("Repita a Senha", driver.findElement(By.id("senha_cliente2_erro")).getText());
	}

	/*
	 * Testando se é possível criar um usuário jurídico com o input de confirmação
	 * senha diferente do input de senha.
	 */
	@Test
	void testeSenhaDeConfirmacaoDiferente() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "123456798",
				"123456789");

		driver.findElement(By.cssSelector(".botao-commerce-img")).click();

		assertTrue(driver.findElement(By.id("senha_cliente2_erro")).isDisplayed());
		assertEquals("Sua senha está diferente da confirmação. Por favor, digite-as novamente.",
				driver.findElement(By.id("senha_cliente2_erro")).getText());
	}

	/*
	 * Testando se o nivel de senha aponta baixo para uma senha simples.
	 */
	@Test
	void testeSenhaBaixa() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "123456798",
				"123456789");

		assertTrue(driver.findElement(By.id("seguranca_senha")).isDisplayed());

		String nivelSenha = driver.findElement(By.id("seguranca_senha")).getAttribute("innerText");
		String procurarPor = "Baixa";

		assertTrue(nivelSenha.toLowerCase().contains(procurarPor.toLowerCase()));
	}

	/*
	 * Testando se o nivel de senha aponta médio para uma senha média.
	 */
	@Test
	void testeSenhaMedia() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "TMedia", "TMedia");

		assertTrue(driver.findElement(By.id("seguranca_senha")).isDisplayed());

		String nivelSenha = driver.findElement(By.id("seguranca_senha")).getAttribute("innerText");
		String procurarPor = "Média";

		assertTrue(nivelSenha.toLowerCase().contains(procurarPor.toLowerCase()));
	}

	/*
	 * Testando se o nivel de senha aponta alto para uma senha alta.
	 */
	@Test
	void testeSenhaAlta() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "TAlta-12", "TAlta-12");

		assertTrue(driver.findElement(By.id("seguranca_senha")).isDisplayed());

		String nivelSenha = driver.findElement(By.id("seguranca_senha")).getAttribute("innerText");
		String procurarPor = "Alta";

		assertTrue(nivelSenha.toLowerCase().contains(procurarPor.toLowerCase()));
	}

	/*
	 * Testando se é possível desmarcar o checkbox.
	 */
	@Test
	void testeCheckboxIniciaMarcada() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "TAlta-12", "TAlta-12");

		assertTrue(driver.findElement(By.id("email")).isSelected());
	}

	/*
	 * Testando se é possível desmarcar o checkbox.
	 */
	@Test
	void testeCheckboxDesmarca() {
		driver.findElement(By.id("PessoaJuridica")).click();

		this.setLegalPerson("Microsoft", "994542976", "48397112000154", "Fulano de Tal", "01012000", "69296495032",
				"8737999999", "87981999999", "ts-selenium@gmail.com", "ts-selenium@gmail.com", "TAlta-12", "TAlta-12");

		if (driver.findElement(By.id("email")).isSelected())
			driver.findElement(By.id("email")).click();

		assertFalse(driver.findElement(By.id("email")).isSelected());
	}
}
