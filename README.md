# Perch Search

## Como o projeto foi construido?
### Execute comando usando Maven
```
mvn archetype:generate -DarchetypeGroupId=net.ltgt.gwt.archetypes -DarchetypeVersion=LATEST -DarchetypeArtifactId=modular-webapp
```
> Conforme a documentação: https://www.gwtproject.org/gettingstarted-v2.html

## Como executar o projeto?

### Inicialize o projeto GWT
```
mvn gwt:codeserver -pl perch-search-client -am
```

### Inicialize o servidor web
```
mvn jetty:run -pl perch-search-server -am -Denv=dev
```