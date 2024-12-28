package io.github.gabbraga;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class App implements EntryPoint {

	final Label httpLabel = new Label("Enter the EndPoint");
	final Label httpMethodLabel = new Label("Enter the Method");
	final Label resultLabel = new Label("No results");
	final TextBox httpField = new TextBox();
	final ListBox httpMethodField = new ListBox();
	final Button sendButton = new Button("Send");
	final VerticalPanel panel = new VerticalPanel();

	final String standardUrlApi = "https://api.github.com/users/gab-braga/repos";

	private RequestBuilder.Method getRequestMethod(String option) {
		switch (option) {
			case "GET":
				return RequestBuilder.GET;
			case "POST":
				return RequestBuilder.POST;
			case "PUT":
				return RequestBuilder.PUT;
			case "DELETE":
				return RequestBuilder.DELETE;
		}
		return null;
	}

	class RequestTestHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			final String url = httpField.getText();
			final String method = httpMethodField.getSelectedItemText();

			if(method == null || method.isEmpty()) {
				httpMethodField.setFocus(true);
			} else if (url == null || url.isEmpty()) {
				httpField.setFocus(true);
			} else {
				final RequestBuilder.Method requestMethod = getRequestMethod(method);
				final RequestBuilder builder = new RequestBuilder(requestMethod, url);
				try {
					builder.sendRequest(null, new RequestCallback() {
						@Override
						public void onResponseReceived(Request request, Response response) {
							resultLabel.setText(response.getText());
						}

						@Override
						public void onError(Request request, Throwable throwable) {
							System.out.println("ERROR: " + throwable);
						}
					});
				} catch (RequestException e) {
					throw new RuntimeException(e);
				}
			}
        }
	}

	public void onModuleLoad() {
		httpField.setText(standardUrlApi);

		httpMethodField.addItem("GET");
		httpMethodField.addItem("POST");
		httpMethodField.addItem("PUT");
		httpMethodField.addItem("DELETE");

		httpLabel.addStyleName("labelForm");
		httpMethodLabel.addStyleName("labelForm");
		resultLabel.addStyleName("resultLabel");
		httpField.addStyleName("inputForm");
		httpMethodField.addStyleName("inputForm");
		sendButton.addStyleName("sendButton");
		panel.addStyleName("panel");

		panel.add(httpLabel);
		panel.add(httpField);
		panel.add(httpMethodLabel);
		panel.add(httpMethodField);
		panel.add(sendButton);
		panel.add(resultLabel);

		RootPanel.get().add(panel);

		sendButton.addClickHandler(new RequestTestHandler());
	}
}
