package core;

import static core.DriverFactory.getDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {
	public void escrever(By by, String texto) {
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public void clicar(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarPorTexto(String texto) {
		clicar(By.xpath("//*[@text='"+texto+"']"));
	}
	
	public void selecionarCombo(By by, String valor) {
		getDriver().findElement(by).click();
		clicarPorTexto(valor);
	}
	
	public boolean isCheckMarcado(By by) {
		return getDriver().findElement(by).getAttribute("checked").equals("true");
	}
	
	public boolean existeElementoPorTexto(String texto) {
		List<MobileElement> elementos = getDriver().findElements(By.xpath("//*[@text='"+texto+"']"));
		return elementos.size() > 0;
	}
	
	public String obterTituloAlerta() {
		return obterTexto(By.id("android:id/alertTitle"));
	}
	
	public String obterMensagemAlerta() {
		return obterTexto(By.id("android:id/message"));
	}
	
	public void tap(int x, int y) {
		new TouchAction(getDriver()).press(PointOption.point(x, y)).perform();
	}
	
	public void scrollDown() {
		scroll(0.9, 0.1);
	}
	
	public void scrollUP() {
		scroll(0.1, 0.9);
	}
	
	public void swipeLeft() {
		scroll(0.1, 0.9);
	}
	
	public void swipeRight() {
		scroll(0.9, 0.1);
	}
	
	public void scroll(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();
		
		int x = size.width / 2;
		int yInicial = (int) (size.height * inicio);
		int yFinal = (int) (size.height * fim);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new TouchAction(getDriver())
	        .press(PointOption.point(x, yInicial))
	        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
	        .moveTo(PointOption.point(x, yFinal))
	        .release()
	        .perform();
	}
	
	public void swipe(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();
		
		int y = size.height / 2;
		int xInicial = (int) (size.width * inicio);
		int xFinal = (int) (size.width * fim);
		
		new TouchAction(getDriver())
	        .press(PointOption.point(xInicial, y))
	        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
	        .moveTo(PointOption.point(xFinal, y))
	        .release()
	        .perform();
		
	}
	
	public void swipeElement(MobileElement element ,double inicio, double fim) {
		int y = element.getLocation().y + (element.getSize().height / 2);
		int xInicial = (int) (element.getSize().width * inicio);
		int xFinal = (int) (element.getSize().width * fim);
		
		new TouchAction(getDriver())
	        .press(PointOption.point(xInicial, y))
	        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
	        .moveTo(PointOption.point(xFinal, y))
	        .release()
	        .perform();
		
	}
	
	public void cliqueLongo() {
		new AndroidTouchAction(getDriver()).longPress(ElementOption.element(getDriver().findElement(By.xpath("//*[@text='Conta para alterar']")))).perform();
	
	}
}
