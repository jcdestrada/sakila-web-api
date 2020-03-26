package com.helloworld;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.service.ActorService;
import com.helloworld.view.ActorVO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class ActorIntegrationTests {
	@Value("8080")
	private int port;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ActorService mockService;

	private TestRestTemplate testRestTemplate;
	private static String ACTOR_ALL = "/actor/all";
	private static String ACTOR_SEARCH_NAME = "actor/search/{name}";
	private static String ACTOR_SEARCH_ID = "actor/id/{id}";

	@BeforeAll
	void setUp() {
		testRestTemplate = new TestRestTemplate();
	}

	@Test
	public void actorHelloWorld() throws Exception {
		when(mockService.helloWorld()).thenReturn("Hello World");

		this.mockMvc.perform(get("/actor/helloworld")).andDo(print()).andExpect(status().isOk())
				.andExpect((content().string("Hello World")));

	}

	@Test
	public void actorAllResponseOK() throws Exception {
		ParameterizedTypeReference<List<ActorVO>> myBean = new ParameterizedTypeReference<List<ActorVO>>() {
		};
		ResponseEntity<List<ActorVO>> response = testRestTemplate.exchange(createURLWithPort("/actor/all"),
				HttpMethod.GET, null, myBean);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void actorAllResponseBody() throws Exception {
		ParameterizedTypeReference<List<ActorVO>> myBean = new ParameterizedTypeReference<List<ActorVO>>() {
		};
		TypeReference<List<ActorVO>> myJSONBean = new TypeReference<List<ActorVO>>() {
		};
		ResponseEntity<List<ActorVO>> response = testRestTemplate.exchange(createURLWithPort(ACTOR_ALL), HttpMethod.GET,
				null, myBean);
		String expectedJSON = "[{\"actorId\":1,\"firstName\":\"PENELOPE\",\"lastName\":\"GUINESS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":2,\"firstName\":\"NICK\",\"lastName\":\"WAHLBERG\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":3,\"firstName\":\"ED\",\"lastName\":\"CHASE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":4,\"firstName\":\"JENNIFER\",\"lastName\":\"DAVIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":5,\"firstName\":\"JOHNNY\",\"lastName\":\"LOLLOBRIGIDA\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":6,\"firstName\":\"BETTE\",\"lastName\":\"NICHOLSON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":7,\"firstName\":\"GRACE\",\"lastName\":\"MOSTEL\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":8,\"firstName\":\"MATTHEW\",\"lastName\":\"JOHANSSON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":9,\"firstName\":\"JOE\",\"lastName\":\"SWANK\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":10,\"firstName\":\"CHRISTIAN\",\"lastName\":\"GABLE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":11,\"firstName\":\"ZERO\",\"lastName\":\"CAGE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":12,\"firstName\":\"KARL\",\"lastName\":\"BERRY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":13,\"firstName\":\"UMA\",\"lastName\":\"WOOD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":14,\"firstName\":\"VIVIEN\",\"lastName\":\"BERGEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":15,\"firstName\":\"CUBA\",\"lastName\":\"OLIVIER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":16,\"firstName\":\"FRED\",\"lastName\":\"COSTNER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":17,\"firstName\":\"HELEN\",\"lastName\":\"VOIGHT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":18,\"firstName\":\"DAN\",\"lastName\":\"TORN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":19,\"firstName\":\"BOB\",\"lastName\":\"FAWCETT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":20,\"firstName\":\"LUCILLE\",\"lastName\":\"TRACY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":21,\"firstName\":\"KIRSTEN\",\"lastName\":\"PALTROW\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":22,\"firstName\":\"ELVIS\",\"lastName\":\"MARX\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":23,\"firstName\":\"SANDRA\",\"lastName\":\"KILMER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":24,\"firstName\":\"CAMERON\",\"lastName\":\"STREEP\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":25,\"firstName\":\"KEVIN\",\"lastName\":\"BLOOM\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":26,\"firstName\":\"RIP\",\"lastName\":\"CRAWFORD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":27,\"firstName\":\"JULIA\",\"lastName\":\"MCQUEEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":28,\"firstName\":\"WOODY\",\"lastName\":\"HOFFMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":29,\"firstName\":\"ALEC\",\"lastName\":\"WAYNE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":30,\"firstName\":\"SANDRA\",\"lastName\":\"PECK\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":31,\"firstName\":\"SISSY\",\"lastName\":\"SOBIESKI\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":32,\"firstName\":\"TIM\",\"lastName\":\"HACKMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":33,\"firstName\":\"MILLA\",\"lastName\":\"PECK\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":34,\"firstName\":\"AUDREY\",\"lastName\":\"OLIVIER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":35,\"firstName\":\"JUDY\",\"lastName\":\"DEAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":36,\"firstName\":\"BURT\",\"lastName\":\"DUKAKIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":37,\"firstName\":\"VAL\",\"lastName\":\"BOLGER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":38,\"firstName\":\"TOM\",\"lastName\":\"MCKELLEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":39,\"firstName\":\"GOLDIE\",\"lastName\":\"BRODY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":40,\"firstName\":\"JOHNNY\",\"lastName\":\"CAGE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":41,\"firstName\":\"JODIE\",\"lastName\":\"DEGENERES\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":42,\"firstName\":\"TOM\",\"lastName\":\"MIRANDA\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":43,\"firstName\":\"KIRK\",\"lastName\":\"JOVOVICH\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":44,\"firstName\":\"NICK\",\"lastName\":\"STALLONE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":45,\"firstName\":\"REESE\",\"lastName\":\"KILMER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":46,\"firstName\":\"PARKER\",\"lastName\":\"GOLDBERG\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":47,\"firstName\":\"JULIA\",\"lastName\":\"BARRYMORE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":48,\"firstName\":\"FRANCES\",\"lastName\":\"DAY-LEWIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":49,\"firstName\":\"ANNE\",\"lastName\":\"CRONYN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":50,\"firstName\":\"NATALIE\",\"lastName\":\"HOPKINS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":51,\"firstName\":\"GARY\",\"lastName\":\"PHOENIX\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":52,\"firstName\":\"CARMEN\",\"lastName\":\"HUNT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":53,\"firstName\":\"MENA\",\"lastName\":\"TEMPLE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":54,\"firstName\":\"PENELOPE\",\"lastName\":\"PINKETT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":55,\"firstName\":\"FAY\",\"lastName\":\"KILMER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":56,\"firstName\":\"DAN\",\"lastName\":\"HARRIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":57,\"firstName\":\"JUDE\",\"lastName\":\"CRUISE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":58,\"firstName\":\"CHRISTIAN\",\"lastName\":\"AKROYD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":59,\"firstName\":\"DUSTIN\",\"lastName\":\"TAUTOU\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":60,\"firstName\":\"HENRY\",\"lastName\":\"BERRY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":61,\"firstName\":\"CHRISTIAN\",\"lastName\":\"NEESON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":62,\"firstName\":\"JAYNE\",\"lastName\":\"NEESON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":63,\"firstName\":\"CAMERON\",\"lastName\":\"WRAY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":64,\"firstName\":\"RAY\",\"lastName\":\"JOHANSSON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":65,\"firstName\":\"ANGELA\",\"lastName\":\"HUDSON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":66,\"firstName\":\"MARY\",\"lastName\":\"TANDY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":67,\"firstName\":\"JESSICA\",\"lastName\":\"BAILEY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":68,\"firstName\":\"RIP\",\"lastName\":\"WINSLET\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":69,\"firstName\":\"KENNETH\",\"lastName\":\"PALTROW\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":70,\"firstName\":\"MICHELLE\",\"lastName\":\"MCCONAUGHEY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":71,\"firstName\":\"ADAM\",\"lastName\":\"GRANT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":72,\"firstName\":\"SEAN\",\"lastName\":\"WILLIAMS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":73,\"firstName\":\"GARY\",\"lastName\":\"PENN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":74,\"firstName\":\"MILLA\",\"lastName\":\"KEITEL\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":75,\"firstName\":\"BURT\",\"lastName\":\"POSEY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":76,\"firstName\":\"ANGELINA\",\"lastName\":\"ASTAIRE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":77,\"firstName\":\"CARY\",\"lastName\":\"MCCONAUGHEY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":78,\"firstName\":\"GROUCHO\",\"lastName\":\"SINATRA\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":79,\"firstName\":\"MAE\",\"lastName\":\"HOFFMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":80,\"firstName\":\"RALPH\",\"lastName\":\"CRUZ\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":81,\"firstName\":\"SCARLETT\",\"lastName\":\"DAMON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":82,\"firstName\":\"WOODY\",\"lastName\":\"JOLIE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":83,\"firstName\":\"BEN\",\"lastName\":\"WILLIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":84,\"firstName\":\"JAMES\",\"lastName\":\"PITT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":85,\"firstName\":\"MINNIE\",\"lastName\":\"ZELLWEGER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":86,\"firstName\":\"GREG\",\"lastName\":\"CHAPLIN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":87,\"firstName\":\"SPENCER\",\"lastName\":\"PECK\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":88,\"firstName\":\"KENNETH\",\"lastName\":\"PESCI\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":89,\"firstName\":\"CHARLIZE\",\"lastName\":\"DENCH\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":90,\"firstName\":\"SEAN\",\"lastName\":\"GUINESS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":91,\"firstName\":\"CHRISTOPHER\",\"lastName\":\"BERRY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":92,\"firstName\":\"KIRSTEN\",\"lastName\":\"AKROYD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":93,\"firstName\":\"ELLEN\",\"lastName\":\"PRESLEY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":94,\"firstName\":\"KENNETH\",\"lastName\":\"TORN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":95,\"firstName\":\"DARYL\",\"lastName\":\"WAHLBERG\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":96,\"firstName\":\"GENE\",\"lastName\":\"WILLIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":97,\"firstName\":\"MEG\",\"lastName\":\"HAWKE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":98,\"firstName\":\"CHRIS\",\"lastName\":\"BRIDGES\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":99,\"firstName\":\"JIM\",\"lastName\":\"MOSTEL\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":100,\"firstName\":\"SPENCER\",\"lastName\":\"DEPP\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":101,\"firstName\":\"SUSAN\",\"lastName\":\"DAVIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":102,\"firstName\":\"WALTER\",\"lastName\":\"TORN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":103,\"firstName\":\"MATTHEW\",\"lastName\":\"LEIGH\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":104,\"firstName\":\"PENELOPE\",\"lastName\":\"CRONYN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":105,\"firstName\":\"SIDNEY\",\"lastName\":\"CROWE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":106,\"firstName\":\"GROUCHO\",\"lastName\":\"DUNST\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":107,\"firstName\":\"GINA\",\"lastName\":\"DEGENERES\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":108,\"firstName\":\"WARREN\",\"lastName\":\"NOLTE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":109,\"firstName\":\"SYLVESTER\",\"lastName\":\"DERN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":110,\"firstName\":\"SUSAN\",\"lastName\":\"DAVIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":111,\"firstName\":\"CAMERON\",\"lastName\":\"ZELLWEGER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":112,\"firstName\":\"RUSSELL\",\"lastName\":\"BACALL\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":113,\"firstName\":\"MORGAN\",\"lastName\":\"HOPKINS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":114,\"firstName\":\"MORGAN\",\"lastName\":\"MCDORMAND\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":115,\"firstName\":\"HARRISON\",\"lastName\":\"BALE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":116,\"firstName\":\"DAN\",\"lastName\":\"STREEP\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":117,\"firstName\":\"RENEE\",\"lastName\":\"TRACY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":118,\"firstName\":\"CUBA\",\"lastName\":\"ALLEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":119,\"firstName\":\"WARREN\",\"lastName\":\"JACKMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":120,\"firstName\":\"PENELOPE\",\"lastName\":\"MONROE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":121,\"firstName\":\"LIZA\",\"lastName\":\"BERGMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":122,\"firstName\":\"SALMA\",\"lastName\":\"NOLTE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":123,\"firstName\":\"JULIANNE\",\"lastName\":\"DENCH\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":124,\"firstName\":\"SCARLETT\",\"lastName\":\"BENING\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":125,\"firstName\":\"ALBERT\",\"lastName\":\"NOLTE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":126,\"firstName\":\"FRANCES\",\"lastName\":\"TOMEI\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":127,\"firstName\":\"KEVIN\",\"lastName\":\"GARLAND\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":128,\"firstName\":\"CATE\",\"lastName\":\"MCQUEEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":129,\"firstName\":\"DARYL\",\"lastName\":\"CRAWFORD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":130,\"firstName\":\"GRETA\",\"lastName\":\"KEITEL\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":131,\"firstName\":\"JANE\",\"lastName\":\"JACKMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":132,\"firstName\":\"ADAM\",\"lastName\":\"HOPPER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":133,\"firstName\":\"RICHARD\",\"lastName\":\"PENN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":134,\"firstName\":\"GENE\",\"lastName\":\"HOPKINS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":135,\"firstName\":\"RITA\",\"lastName\":\"REYNOLDS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":136,\"firstName\":\"ED\",\"lastName\":\"MANSFIELD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":137,\"firstName\":\"MORGAN\",\"lastName\":\"WILLIAMS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":138,\"firstName\":\"LUCILLE\",\"lastName\":\"DEE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":139,\"firstName\":\"EWAN\",\"lastName\":\"GOODING\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":140,\"firstName\":\"WHOOPI\",\"lastName\":\"HURT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":141,\"firstName\":\"CATE\",\"lastName\":\"HARRIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":142,\"firstName\":\"JADA\",\"lastName\":\"RYDER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":143,\"firstName\":\"RIVER\",\"lastName\":\"DEAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":144,\"firstName\":\"ANGELA\",\"lastName\":\"WITHERSPOON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":145,\"firstName\":\"KIM\",\"lastName\":\"ALLEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":146,\"firstName\":\"ALBERT\",\"lastName\":\"JOHANSSON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":147,\"firstName\":\"FAY\",\"lastName\":\"WINSLET\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":148,\"firstName\":\"EMILY\",\"lastName\":\"DEE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":149,\"firstName\":\"RUSSELL\",\"lastName\":\"TEMPLE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":150,\"firstName\":\"JAYNE\",\"lastName\":\"NOLTE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":151,\"firstName\":\"GEOFFREY\",\"lastName\":\"HESTON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":152,\"firstName\":\"BEN\",\"lastName\":\"HARRIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":153,\"firstName\":\"MINNIE\",\"lastName\":\"KILMER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":154,\"firstName\":\"MERYL\",\"lastName\":\"GIBSON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":155,\"firstName\":\"IAN\",\"lastName\":\"TANDY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":156,\"firstName\":\"FAY\",\"lastName\":\"WOOD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":157,\"firstName\":\"GRETA\",\"lastName\":\"MALDEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":158,\"firstName\":\"VIVIEN\",\"lastName\":\"BASINGER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":159,\"firstName\":\"LAURA\",\"lastName\":\"BRODY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":160,\"firstName\":\"CHRIS\",\"lastName\":\"DEPP\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":161,\"firstName\":\"HARVEY\",\"lastName\":\"HOPE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":162,\"firstName\":\"OPRAH\",\"lastName\":\"KILMER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":163,\"firstName\":\"CHRISTOPHER\",\"lastName\":\"WEST\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":164,\"firstName\":\"HUMPHREY\",\"lastName\":\"WILLIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":165,\"firstName\":\"AL\",\"lastName\":\"GARLAND\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":166,\"firstName\":\"NICK\",\"lastName\":\"DEGENERES\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":167,\"firstName\":\"LAURENCE\",\"lastName\":\"BULLOCK\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":168,\"firstName\":\"WILL\",\"lastName\":\"WILSON\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":169,\"firstName\":\"KENNETH\",\"lastName\":\"HOFFMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":170,\"firstName\":\"MENA\",\"lastName\":\"HOPPER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":171,\"firstName\":\"OLYMPIA\",\"lastName\":\"PFEIFFER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":172,\"firstName\":\"GROUCHO\",\"lastName\":\"WILLIAMS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":173,\"firstName\":\"ALAN\",\"lastName\":\"DREYFUSS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":174,\"firstName\":\"MICHAEL\",\"lastName\":\"BENING\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":175,\"firstName\":\"WILLIAM\",\"lastName\":\"HACKMAN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":176,\"firstName\":\"JON\",\"lastName\":\"CHASE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":177,\"firstName\":\"GENE\",\"lastName\":\"MCKELLEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":178,\"firstName\":\"LISA\",\"lastName\":\"MONROE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":179,\"firstName\":\"ED\",\"lastName\":\"GUINESS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":180,\"firstName\":\"JEFF\",\"lastName\":\"SILVERSTONE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":181,\"firstName\":\"MATTHEW\",\"lastName\":\"CARREY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":182,\"firstName\":\"DEBBIE\",\"lastName\":\"AKROYD\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":183,\"firstName\":\"RUSSELL\",\"lastName\":\"CLOSE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":184,\"firstName\":\"HUMPHREY\",\"lastName\":\"GARLAND\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":185,\"firstName\":\"MICHAEL\",\"lastName\":\"BOLGER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":186,\"firstName\":\"JULIA\",\"lastName\":\"ZELLWEGER\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":187,\"firstName\":\"RENEE\",\"lastName\":\"BALL\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":188,\"firstName\":\"ROCK\",\"lastName\":\"DUKAKIS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":189,\"firstName\":\"CUBA\",\"lastName\":\"BIRCH\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":190,\"firstName\":\"AUDREY\",\"lastName\":\"BAILEY\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":191,\"firstName\":\"GREGORY\",\"lastName\":\"GOODING\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":192,\"firstName\":\"JOHN\",\"lastName\":\"SUVARI\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":193,\"firstName\":\"BURT\",\"lastName\":\"TEMPLE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":194,\"firstName\":\"MERYL\",\"lastName\":\"ALLEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":195,\"firstName\":\"JAYNE\",\"lastName\":\"SILVERSTONE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":196,\"firstName\":\"BELA\",\"lastName\":\"WALKEN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":197,\"firstName\":\"REESE\",\"lastName\":\"WEST\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":198,\"firstName\":\"MARY\",\"lastName\":\"KEITEL\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":199,\"firstName\":\"JULIA\",\"lastName\":\"FAWCETT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":200,\"firstName\":\"THORA\",\"lastName\":\"TEMPLE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":201,\"firstName\":\"JC\",\"lastName\":\"POGI\",\"lastUpdate\":\"2020-03-20T22:39:32+08:00\"},{\"actorId\":204,\"firstName\":\"JC\",\"lastName\":\"POGI2\",\"lastUpdate\":\"2020-03-20T23:04:35+08:00\"}]";
		String actual = objectMapper.writeValueAsString(response.getBody());
		List<ActorVO> expectedObject = objectMapper.readValue(expectedJSON, myJSONBean);
		String expected = objectMapper.writeValueAsString(expectedObject);
		System.out.println("actual");
		System.out.println(actual);
		System.out.println("expected");
		System.out.println(expected);

		JSONAssert.assertEquals(expected, actual, false);
	}

	@Test
	public void actorSearchByIdResponseOK() throws Exception {
		ResponseEntity<ActorVO> response = testRestTemplate.exchange(createURLWithPort(ACTOR_SEARCH_ID, 1),
				HttpMethod.GET, null, ActorVO.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void actorSearchByIdResponseBody() throws Exception {
		ResponseEntity<ActorVO> response = testRestTemplate.exchange(createURLWithPort(ACTOR_SEARCH_ID, 1),
				HttpMethod.GET, null, ActorVO.class);
		String expectedJSON = "{\"actorId\":1,\"firstName\":\"PENELOPE\",\"lastName\":\"GUINESS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"}";
		String actual = objectMapper.writeValueAsString(response.getBody());
		ActorVO expectedObject = objectMapper.readValue(expectedJSON, ActorVO.class);
		String expected = objectMapper.writeValueAsString(expectedObject);
		System.out.println("actual");
		System.out.println(actual);
		System.out.println("expected");
		System.out.println(expected);

		JSONAssert.assertEquals(expected, actual, false);
	}

	@Test
	public void actorSearchByNameResponseOK() throws Exception {
		ParameterizedTypeReference<List<ActorVO>> myBean = new ParameterizedTypeReference<List<ActorVO>>() {
		};
		ResponseEntity<List<ActorVO>> response = testRestTemplate.exchange(createURLWithPort(ACTOR_SEARCH_NAME, "PENELOPE"), HttpMethod.GET,
				null, myBean);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void actorSearchByNameResponseBody() throws Exception {
		String expectedJSON = "[{\"actorId\":1,\"firstName\":\"PENELOPE\",\"lastName\":\"GUINESS\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":54,\"firstName\":\"PENELOPE\",\"lastName\":\"PINKETT\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":104,\"firstName\":\"PENELOPE\",\"lastName\":\"CRONYN\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"},{\"actorId\":120,\"firstName\":\"PENELOPE\",\"lastName\":\"MONROE\",\"lastUpdate\":\"2006-02-15T12:34:33+08:00\"}]";

		ParameterizedTypeReference<List<ActorVO>> myBean = new ParameterizedTypeReference<List<ActorVO>>() {
		};
		TypeReference<List<ActorVO>> myJSONBean = new TypeReference<List<ActorVO>>() {
		};
		ResponseEntity<List<ActorVO>> response = testRestTemplate.exchange(createURLWithPort(ACTOR_SEARCH_NAME, "PENELOPE"), HttpMethod.GET,
				null, myBean);
		String actual = objectMapper.writeValueAsString(response.getBody());
		List<ActorVO> expectedObject = objectMapper.readValue(expectedJSON, myJSONBean);
		String expected = objectMapper.writeValueAsString(expectedObject);
		System.out.println("actual");
		System.out.println(actual);
		System.out.println("expected");
		System.out.println(expected);

		JSONAssert.assertEquals(expected, actual, false);
	}

	private String createURLWithPort(String path, Object uriVariableValues) {
		System.out.println("http://localhost:" + port + path);
		return UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8080).path(path)
				.buildAndExpand(uriVariableValues).toUriString();
	}

	private String createURLWithPort(String path) {
		System.out.println("http://localhost:" + port + path);
		return UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8080).path(path).toUriString();
	}

}
