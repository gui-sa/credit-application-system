<h1>request-credit-system</h1>
<p align="center">API Rest para um Sistema de Analise de Solicitação de Crédito</p>
<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v17-blue.svg" />
    </a>
    <a alt="Kotlin">
        <img src="https://img.shields.io/badge/Kotlin-v1.7.22-purple.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v3.0.3-brightgreen.svg" />
    </a>
    <a alt="Gradle">
        <img src="https://img.shields.io/badge/Gradle-v7.6-lightgreen.svg" />
    </a>
    <a alt="H2 ">
        <img src="https://img.shields.io/badge/H2-v2.1.214-darkblue.svg" />
    </a>
    <a alt="Flyway">
        <img src="https://img.shields.io/badge/Flyway-v9.5.1-red.svg">
    </a>
</p>

<h3>Teste Unitario</h3>
<p>Algumas Anotações e Metodos:</p>
<ol>
	<li><b>@ExtendWith(MockKExtension::class)</b> Utilizado logo acima da classe de testes unitarios para extender as anotações do Junit</li>
	<li><b>@ActiveProfiles("test")</b> Utilizado logo acima da classe para indicar ao spring qual application.properties sera utilizado</li>
    <li><b>@MockK lateinit var</b> Utilizado para indicar ao spring que essa variavel sera Mockada, ou seja, falsificada/simulada e que por isso ela sera iniciada mais tarde. </li>
    <li><b>@InjectMockKs</b> Utilizado para indicar ao spring que essa variavel sera vitima das variaveis mockadas... Em suma, ela recebera variaveis mockadas e tera que trabalhar ainda assim</li>
    <li><b>@BeforeEach</b> Esse metodo sera rodado antes de o metodo teste da classe rodar. Para todos os metodos</li>
    <li><b>@AfterEach</b> Esse metodo sera rodado depois de o metodo teste da classe rodar. Para todos os metodos</li>
    <li><b>MockKAnnotations.init(this)</b> Quando possui mais de um MockK e InjectMockKs para iniciar um novo MockK</li>
    <li><b>unmockkAll()</b> Para poder liberar as memorias das variaveis MockK</li>
    <li><b>@Test</b> Acima do metodo e anota ele como teste </li>
    <li><b>Assertions</b> Classe do Junit ou AssertJ para realizar uma checagem </li>
    <li><b>every{ /* Caracteristica/Metodo de uma variavel Mock */} returns /* O que desejo que retorne para comparar nos injectMockK*/</b> Usado para criar uma regra que transpassa e força um comportamento</li>
    <li><b>verify(exactly = /* quantidade de vezes rodada */){ /* Caracteristica/Metodo de uma variavel Mock */ }</b> Usado para checar recorrencias de chamadas de certos metodos</li>
    <li><b>companion object{}</b> Usado para criar um singleton - simulando caracteristicas static na classe</li>
    <li><b>@DataJpaTest</b> Usado para indicar que essa classe testa usando JPA em um banco de dados em memoria (H2 like) </li>
    <li><b>@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)</b> Indica que esse @DataJpaTest usara as configurações do application-test.properties ... importante lembrar de usar o @ActiveProfiles("test")</li>
    <li><b>@Autowired lateinit var</b> Quando estamos testando um repositorio não podemos MockKar logo o Spring inicializa as variaveis via IoC</li>
    <li><b>testEntityManager.persist( /* Uma funcao que constroi uma classe modelo que e entidade */)</b> da Classe TestEntityManager via Autowired que tem por objetivo colocar esse objeto na memoria e dessa forma começar o teste com informacao. Geralmente com @BeforeEach. Cada teste por se tratar de um banco de dado em memoria remove todos os dados que ali esta</li>
    <li><b>Assertions.assertThat( /* var */).isNotNull</b> Para checar se que ela nao é nula </li>
    <li><b>Assertions.assertThat( /* var A */ ).isSameAs( /* var B */ )</b> checa se são iguais estruturalmente </li>
    <li><b>Assertions.assertThat( /* var */).isExactlyInstanceOf( /* Uma classe */::class.java)</b> Para checar de qual tupo/classe essa variavel é</li>
    <li><b>Assertions.assertThatExceptionOfType( /* An ExceptionType */::class.java)
      .isThrownBy { /* Caracteristica/Metodo de uma variavel Mock */ }
      .withMessage( /* Uma mensagem */)</b> Para checar as Exceções!</li>
    <li><b>@AutoConfigureMockMvc</b> Para iniciar uma classe que fara testes de integração por API </li>
    <li><b>@ContextConfiguration e @SpringBootTest</b> Configuram o Spring Boot para iniciar esses testes</li>
    <li><b>mockMvc.perform()</b> Possui Muitas configurações mas é ele quem testa e averigua as APIs</li>
    <li><b>ObjectMapper</b> Transforma um objeto em um Json ou String para realizar comparações! use %.atributoJson para resgatar esses atributos!</li>
</ol>

<h3>Observações e Conclusões</h3>
<p>Boas Praticas de aplicação e padrões:</p>
<ol>
    <li>É normal existir um companion object responsavel por contruir uma classe com as caracteristicas desejadas. Muitas vezes um construtor (funcao qualquer) é reutilizado de outras para gerar outro. É um bom costume gerar um construtor com caracteristicas padroes. </li>
    <li><b>Cada exceção deve ser testada (tanto positivamente tanto negativamente)</b> </li>
    <li><b>Cada regra de negocio deve ser testada</b> </li>
    <li>É um bom costume testar o maximo possivel variações de construção de classe e de recepção de respostas </li>
    <li>Testar casos de borda são geralmente mais importantes do que casos normais</li>
    <li>Seja criativo e teste situações chulas...</li>
    <li>Testes de integração automatizado tem por objetivo ganhar tempo e procurar identificar e garantir que os componentes continuam funcionando mesmo apos a adição de uma nova feature</li>
    <li><b>Testes automatizados rules!</b> É ele quem vai de fato garantir a cobertura e de dar produtividade!</li>
    <li><b>Um teste nunca garante a ausencia de um bug!</b></li>
    <li>Escreva o nome dos metodos @Test com `` pois dessa forma voce pode descreve-lo ali mesmo!</li>
</ol>

<h3>Descrição do Projeto</h3>
<p><a href="https://gist.github.com/cami-la/560b455b901778391abd2c9edea81286">https://gist.github.com/cami-la/560b455b901778391abd2c9edea81286</a></p>
<figure>
<p align="center">
  <img src="https://i.imgur.com/7phya16.png" height="350" width="450" alt="API para Sistema de Avaliação de Créditos"/><br>
  Diagrama UML Simplificado de uma API para Sistema de Avaliação de Crédito
</p>
</figure>

<h3>Instrução de Uso</h3>
<p>No Terminal/Console:</p>
<ol>
	<li>Faça um clone do projeto na sua máquina: <code>git clone git@github.com:cami-la/credit-application-system.git</code></li>
	<li>Entre na pasta raiz do projeto: <code>cd </code></li> 
	<li>Execute o comando: <code>./gradlew bootrun</code></li>
</ol>
<h6>** Visando facilitar a demostração da aplicação, recomendo instalar apenas o IntelliJ IDEA e executar o projeto através da IDE **</h6>


<a href="https://drive.google.com/file/d/1wxwioDHS1sKFPq4G7b24tVZb-XMnoj-l/view?usp=share_link"> 🚀 Collection Sacola API - Postman</a><br>

<h3>Autor dessa versão</h3>
<a href="https://www.linkedin.com/in/agostini-guilherme/">

<h3>Autor Real</h3>

<a href="https://www.linkedin.com/in/cami-la/">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/64323124?v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Camila Cavalcante</b></sub></a> <a href="https://www.instagram.com/camimi_la/" title="Instagram"></a>

Feito com ❤️ por Cami-la 👋🏽 Entre em contato!

[![Linkedin Badge](https://img.shields.io/badge/-Camila-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/cami-la/)](https://www.linkedin.com/in/cami-la/)
[![Gmail Badge](https://img.shields.io/badge/-camiladsantoscavalcante@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:camiladsantoscavalcante@gmail.com)](mailto:camiladsantoscavalcante@gmail.com)
<hr>
<h3>Contribuindo</h3>

Este repositório foi criado para fins de estudo, então contribua com ele.<br>
Se te ajudei de alguma forma, ficarei feliz em saber. Caso você conheça alguém que se identifique com o conteúdo, não
deixe de compatilhar.

Se possível:

⭐️ Star o projeto

🐛 Encontrar e relatar issues

