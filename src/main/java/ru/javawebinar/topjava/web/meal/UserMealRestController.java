package ru.javawebinar.topjava.web.meal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

import static org.springframework.format.annotation.DateTimeFormat.*;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(UserMealRestController.REST_URL)
public class UserMealRestController extends AbstractUserMealController
{
	static final String REST_URL = "/rest/meals";

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserMealWithExceed> getAll()
	{
		return super.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserMeal getById(@PathVariable int id)
	{
		return super.get(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserMeal> createWithLocation(@RequestBody UserMeal userMeal)
	{
		UserMeal meal = super.create(userMeal);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(REST_URL + "{id}")
				.buildAndExpand(meal.getId())
				.toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity<UserMeal>(meal, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id)
	{
		super.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody UserMeal meal, @PathVariable int id)
	{
		meal.setId(id);
		super.update(meal);
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserMealWithExceed> getBetween(@RequestParam(value = "startDate") @DateTimeFormat(iso = ISO.DATE) Date startDate,
											   @RequestParam(value = "endDate") @DateTimeFormat(iso = ISO.DATE) Date endDate,
											   @RequestParam(value = "startTime") @DateTimeFormat(iso = ISO.TIME) Date startTime,
											   @RequestParam(value = "endTime") @DateTimeFormat(iso = ISO.TIME) Date endTime) throws IOException
	{
		// problem here
		// json get in next string with quotes in begin and end - ""{\"endDate\":\"2015-05-30\",\"startTime\":\"10:00:00\",\"endTime\":\"16:00:00\",\"startDate\":\"2015-05-30\"}""
		// String jsonData = new Scanner(request.getInputStream(),"UTF-8").next();
		/*if (jsonData.startsWith("\"") && jsonData.endsWith("\"")) {
			jsonData = jsonData.subSequence(1, jsonData.length() - 2).toString();
		}*/
		/*jsonData = "{\"endDate\":\"2015-05-30\",\"startTime\":\"10:00:00\",\"endTime\":\"16:00:00\",\"startDate\":\"2015-05-30\"}";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> params;
		params = mapper.readValue(jsonData, new TypeReference<Map<String, String>>(){});

		LocalDate startDate = TimeUtil.parseLocalDate((String) params.get("startDate"), TimeUtil.MIN_DATE);
		LocalDate endDate = TimeUtil.parseLocalDate((String) params.get("endDate"), TimeUtil.MAX_DATE);
		LocalTime startTime = TimeUtil.parseLocalTime((String) params.get("startTime"), LocalTime.MIN);
		LocalTime endTime = TimeUtil.parseLocalTime((String) params.get("endTime"), LocalTime.MAX);*/
		return super.getBetween(
				startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime(),
				endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime()
		);
	}
}