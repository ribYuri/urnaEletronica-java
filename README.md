# Urna Eletronica
Sistema de uma urna eletrônica elaborado como trabalho final da matéria de Engenharia de Software II. O sistema foi desenvolvido utilizando java-web e arquitetura MVC, usando servlets e o servidor Apache Tomcat v10, além de conexão com banco de dados PostregreSQL.

# Como Executar
Para inicar precisaremos da Eclipse IDE e do Apache Tomcat v10 já instalado e então seguir os passos:
* Faça o download deste projeto e o abra no Eclipse
* Na parte inferior onde temos a aba *Console*, selecione a aba *Servers* (caso não tenha esta opção, deverá ser ativada)
* Após adicionar o servidor Apache Tomcat, clice com o botão direito em "*Tomcat v10 Server at localhost*" e selecione a opção *"Add and Remove..."*
* Clique no projeto a direita, clique em *add* ao centro e então *finish* na parte inferior

Agora precisaremos do PostregreSQL:
* Após o processo de instalação, clique com o batão direito no database padrão "*postegres*" e selecione *Querry Tool*
* Abra o arquivo *Urna_Script.txt*, copie seu conteúdo, cole na Querry e execute

Ainda precisamos garantir que o caminho e os dados estão corretos para a conexão com o banco. Para isso:
* Entre no diretorio `src/main/java/urna/dao/` e acesse o arquivo *PostgreSqlConnection.java*
* Certifique-se de que o *usuario*, *senha* e *porta* (padrão :5432) estão corretos.
* Caso altere o nome do banco também deverar alterar, neste mesmo arquivo, "postgres" na parte final da *url* para o novo nome

Agora basta selecionar o servidor Tomcat, iniciar e então inserir na url do navegador: *http://localhost:8080/urnaEletronicaWeb/identificar.jsp*
