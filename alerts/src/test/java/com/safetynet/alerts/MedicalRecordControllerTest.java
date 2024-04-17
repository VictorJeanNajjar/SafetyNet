package com.safetynet.alerts;

public class MedicalRecordControllerTest {
    //    @Test
//    public void testAddNewMedicalRecordController() throws Exception {
//        List<String> testList = new ArrayList<>();
//        MedicalRecord medicalRecord = new MedicalRecord();
//        medicalRecord.setFirstName("John");
//        medicalRecord.setLastName("Doe");
//        medicalRecord.setBirthdate("01/01/2001");
//        medicalRecord.setAllergies(testList);
//        medicalRecord.setAllergies(testList);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(medicalRecord)))
//                .andExpect(status().isOk());
//        verify(medicalRecordController).addNewMedicalRecordController(any(MedicalRecord.class));
//    }
//
//    @Test
//    public void testDeleteMedicalRecordController() throws Exception {
//        // Act & Assert
//        mockMvc.perform(delete("/medicalRecord")
//                        .param("firstName", "John")
//                        .param("lastName", "Doe"))
//                .andExpect(status().isOk());
//
//        // Verify that deleteMedicalRecord method is called with correct arguments
//        verify(medicalRecordController, times(1)).deleteMedicalRecordController("John", "Doe");
//    }
//
//    @Test
//    public void testUpdateMedicalRecordController() throws Exception {
//        // Arrange
//        MedicalRecord medicalRecord = new MedicalRecord();
//        medicalRecord.setFirstName("John");
//        medicalRecord.setLastName("Doe");
//
//        // Act & Assert
//        mockMvc.perform(put("/medicalRecord")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\" }"))
//                .andExpect(status().isOk());
//
//        // Verify that updateMedicalRecord method is called with correct arguments
//        verify(medicalRecordController, times(1)).updateMedicalRecordController(medicalRecord);
//    }
}
