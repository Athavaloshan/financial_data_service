package com.example.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Date;
import com.example.springboot.model.PriceRecord;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHomeMsg() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from price controller!")));
	}

  @Test
	public void writeDataToDB() throws Exception {
    Date d1 = new Date(2000, 11, 21);
    PriceRecord priceRecord = new PriceRecord(1, "COMM", d1, 5f, 4f, 2f, 2f, 23.33f, 1.22f);
    ObjectMapper objectMapper = new ObjectMapper();
    // objectMapper.writeValue(new File("target/priceRecord.json"), priceRecord);
    String priceRecordString = objectMapper.writeValueAsString(priceRecord);
    mvc.perform(MockMvcRequestBuilders.post("/api/write").accept(MediaType.APPLICATION_JSON).content(priceRecordString))
    .andExpect(status().isOk());
	}

	// public void readDataFromDB() throws Exception {

	// }

	// public void getPriceFromDB() throws Exception {
		
	// }
}

// package com.bezkoder.spring.files.csv.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;

// import com.bezkoder.spring.files.csv.service.CSVService;
// import com.bezkoder.spring.files.csv.helper.CSVHelper;
// import com.bezkoder.spring.files.csv.message.ResponseMessage;
// import com.bezkoder.spring.files.csv.model.Tutorial;

// @CrossOrigin("http://localhost:8081")
// @Controller
// @RequestMapping("/api/csv")
// public class CSVController {

//   @Autowired
//   CSVService fileService;

//   @PostMapping("/upload")
//   public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//     String message = "";

//     if (CSVHelper.hasCSVFormat(file)) {
//       try {
//         fileService.save(file);

//         message = "Uploaded the file successfully: " + file.getOriginalFilename();
//         return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//       } catch (Exception e) {
//         message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//       }
//     }

//     message = "Please upload a csv file!";
//     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//   }

//   @GetMapping("/tutorials")
//   public ResponseEntity<List<Tutorial>> getAllTutorials() {
//     try {
//       List<Tutorial> tutorials = fileService.getAllTutorials();

//       if (tutorials.isEmpty()) {
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//       }

//       return new ResponseEntity<>(tutorials, HttpStatus.OK);
//     } catch (Exception e) {
//       return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
//   }

// }
