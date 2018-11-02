
package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import org.firstinspires.ftc.robotcore.external.navigation.Position;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ServoTest", group = "Linear")
public class OnBotMovement extends LinearOpMode
{

    private Servo test1;
    private static final double ArmPos= 0.0;
    double servoPos = 0.35;
    double servoPos2 = 0;
    @Override
    public void runOpMode () throws InterruptedException
    {

        test1 = hardwareMap.get(Servo.class, "test1");


        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                test1.setPosition(servoPos);
                telemetry.addData("Target =",+ servoPos + "actual=" + test1.getPosition());
                telemetry.update();

            }


            if (gamepad1.dpad_down) {

               test1.setPosition(servoPos2);
                telemetry.addData("Dpad Dwn, target = ", + servoPos2 + "actual =" +test1.getPosition());
                telemetry.update();
            }
        }


    }


}
