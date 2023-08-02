package org.ms;

import org.ms.model.CustomerModel;
import org.ms.model.StaffModel;

public class Main8 {
    public static void main(String[] args) {
//        StudentModel

//        CustomerModel customerModel = new CustomerModel(100);
//        System.out.println("Hello Customer ID: " + customerModel.getId());

        StaffModel staffModel = new StaffModel();
        staffModel.setId(12);
        staffModel.setName("Kok");
        staffModel.setPosition("Developer");
        System.out.println("Staff One ID: " + staffModel.getId() + " Name: " + staffModel.getName() + " Position: " + staffModel.getPosition());

        StaffModel staffModel2 = new StaffModel("Koko", "Designer");
        System.out.println("Staff Two ID: " + staffModel2.getId() + " Name: " + staffModel2.getName() + " Position: " + staffModel2.getPosition());

        StaffModel staffModel3 = new StaffModel("Tola", "Mobile Developer");
        staffModel3.setId(3);
        System.out.println("Staff Three ID: " + staffModel3.getId() + " Name: " + staffModel3.getName() + " Position: " + staffModel3.getPosition());
    }
}
