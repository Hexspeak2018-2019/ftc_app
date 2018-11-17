package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.Callable;

public class LeadClass {


        public void leadScrewUp ( double distance, double power, double timeout, LinearOpMode linearOpMode) throws Exception {

            HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

            robot.resetMotorsAndEncoders();

            int tolerance = 50;
            int leadScrewPitch = 2;
            int counts = ((int) Math.round(distance / leadScrewPitch * robot.ANDYMARK_TICKS_PER_REV));
//must set direction first
            robot.leadScrewMotor.setDirection(DcMotor.Direction.REVERSE);
//then set position
            robot.leadScrewMotor.setTargetPosition(counts);
//then set the mode
            robot.leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
            robot.runtime.reset();
            robot.leadScrewMotor.setPower(power);

            while (Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                    > tolerance || (linearOpMode.opModeIsActive())) {

                if (robot.runtime.seconds() > timeout) {
                    break;
                }


                robot.localtelemetry.addData("Current LeadScrew Counts:", (robot.leadScrewMotor.getCurrentPosition()));
                robot.localtelemetry.addData("LeadScrew Power:", robot.leadScrewMotor.getPower());
                robot.localtelemetry.addData("LeadScrew Target Pos:", robot.leadScrewMotor.getTargetPosition());
                robot.localtelemetry.update();
            }

            robot.resetMotorsAndEncoders();
        }


        public void leadScrewDown ( double distance, double power, double timeout){
        HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

        robot.resetMotorsAndEncoders();

        int tolerance = 50;
        int leadScrewPitch = 2;
        int counts = (int) Math.round(distance / leadScrewPitch * robot.ANDYMARK_TICKS_PER_REV);
//must set direction first
        robot.leadScrewMotor.setDirection(DcMotor.Direction.REVERSE);
//then set position
        robot.leadScrewMotor.setTargetPosition(-counts);
//then set the mode
        robot.leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
        robot.runtime.reset();
        robot.leadScrewMotor.setPower(power);

        while (Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                > tolerance || Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                > tolerance) {
            if (robot.runtime.seconds() > timeout) {
                break;
            }

            robot.localtelemetry.addData("Current LeadScrew Counts:", (robot.leadScrewMotor.getCurrentPosition()));
            robot.localtelemetry.addData("LeadScrew Power:", robot.leadScrewMotor.getPower());
            robot.localtelemetry.addData("LeadScrew Target Pos:", robot.leadScrewMotor.getTargetPosition());
            robot.localtelemetry.update();
        }

        robot.resetMotorsAndEncoders();
    }

}
