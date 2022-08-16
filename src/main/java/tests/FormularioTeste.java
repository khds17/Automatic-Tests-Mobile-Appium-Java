package tests;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.BaseTest;
import core.DriverFactory;
import io.appium.java_client.MobileBy;
import pages.FormularioPage;
import pages.MenuPage;

public class FormularioTeste extends BaseTest {
			
	private MenuPage menu = new MenuPage();
	private FormularioPage page = new FormularioPage();
	
	@Before
	public void inicializarAppium() throws MalformedURLException {
		menu.acessarFormulario();
	}
	
	
	@Test
	public void devePreencherCampoTexto() throws MalformedURLException {	
		page.escreverNome("Kauan Henrique");	
		assertEquals("Kauan Henrique", page.obterNome());
	}
	
	@Test
	public void deveInteragirCombo() throws MalformedURLException {
		page.selecionarCombo("PS4");
		Assert.assertEquals("PS4", page.obterValorCombo());		
	}
	
	@Test
	public void deveInteragirSwitchCheckbox() throws MalformedURLException {

		Assert.assertFalse(page.isCheckMarcado());
		Assert.assertTrue(page.isSwitchMarcado());
		
		page.clicarCheck();
		page.clicarSwitch();
	
		
		Assert.assertTrue(page.isCheckMarcado());
		Assert.assertFalse(page.isSwitchMarcado());
	}
	
	@Test
	public void deveRealizarCadastro() throws MalformedURLException {	
		page.escreverNome("Kauan Henrique");
		page.selecionarCombo("PS4");
		page.clicarCheck();
		page.clicarSwitch();
		page.salvar();
		
		Assert.assertEquals("Nome: Kauan Henrique", page.obterNomeCadastrado());
		Assert.assertEquals("Console: ps4", page.obterConsoleCadastrado());
		Assert.assertEquals("Checkbox: Marcado", page.obterCheckCadastrado());
		Assert.assertEquals("Switch: Off", page.obterSwitchCadastrado());			
	}

	@Test
	public void deveRealizarCadastroDemorado() throws MalformedURLException {	
		page.escreverNome("Kauan Henrique");
		
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		page.salvarDemorado();
		
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Nome: Kauan Henrique']")));
		
		Assert.assertEquals("Nome: Kauan Henrique", page.obterNomeCadastrado());
	}
	
	@Test
	public void deveAlterarData() {
		page.clicarPorTexto("01/01/2000");
		page.clicarPorTexto("20");
		page.clicarPorTexto("OK");
		Assert.assertTrue(page.existeElementoPorTexto("20/2/2000"));
	}
	
	@Test
	public void deveAlterarHora() {
		page.clicarPorTexto("06:00");
		page.clicar(MobileBy.AccessibilityId("10"));
		page.clicar(MobileBy.AccessibilityId("40"));
		page.clicarPorTexto("OK");
		Assert.assertTrue(page.existeElementoPorTexto("10:40"));
	}
	
	@Test
	public void deveInteragirComSeekbar() {
		page.clicarSeekBar(0.25);
		page.salvar();
	}
	
}
