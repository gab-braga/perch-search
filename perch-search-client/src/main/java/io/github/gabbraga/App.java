package io.github.gabbraga;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class App implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	public void onModuleLoad() {
		final Label titleLabel = new Label("Login");
		final Label emailLabel = new Label("E-mail");
		final Label passLabel = new Label("Senha");
		final TextBox emailField = new TextBox();
		final TextBox passField = new TextBox();
		final Button sendButton = new Button("Entrar");
		final VerticalPanel  vertPanel = new VerticalPanel();

		titleLabel.addStyleName("titleLabel");

		vertPanel.add(titleLabel);
		vertPanel.add(emailLabel);
		vertPanel.add(emailField);
		vertPanel.add(passLabel);
		vertPanel.add(passField);
		vertPanel.add(sendButton);

		RootPanel.get().add(vertPanel);
	}
}
