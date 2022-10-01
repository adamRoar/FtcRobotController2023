package org.firstinspires.ftc.teamcode.RobotLibs;

import static org.firstinspires.ftc.teamcode.RobotLibs.Drivetrain.MotorName.LEFT_BACK;
import static org.firstinspires.ftc.teamcode.RobotLibs.Drivetrain.MotorName.LEFT_FRONT;
import static org.firstinspires.ftc.teamcode.RobotLibs.Drivetrain.MotorName.RIGHT_BACK;
import static org.firstinspires.ftc.teamcode.RobotLibs.Drivetrain.MotorName.RIGHT_FRONT;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.Collections;

public class Drivetrain {
    public enum MotorName {
        LEFT_FRONT,
        LEFT_BACK,
        RIGHT_BACK,
        RIGHT_FRONT
    }

    public DcMotor leftBackMotor;
    public DcMotor rightBackMotor;
    public DcMotor leftFrontMotor;
    public DcMotor rightFrontMotor;

    public Drivetrain(HardwareMap hardwareMap) {
        leftBackMotor = hardwareMap.get(DcMotor.class, LEFT_BACK.name());
        rightBackMotor = hardwareMap.get(DcMotor.class, RIGHT_BACK.name());
        leftFrontMotor = hardwareMap.get(DcMotor.class, LEFT_FRONT.name());
        rightFrontMotor = hardwareMap.get(DcMotor.class, RIGHT_FRONT.name());

        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void setPowers(double LF, double LB, double RB, double RF) {
        leftBackMotor.setPower(LB);
        rightBackMotor.setPower(RB);
        leftFrontMotor.setPower(LF);
        rightFrontMotor.setPower(RF);
    }

    public void mecanumDrive(double forward, double strafe, double rotation) {
        double[] motorPowers = new double[4];
        motorPowers[LEFT_FRONT.ordinal()] = forward + strafe + rotation;
        motorPowers[LEFT_BACK.ordinal()] = forward - strafe + rotation;
        motorPowers[RIGHT_BACK.ordinal()] = forward + strafe - rotation;
        motorPowers[RIGHT_FRONT.ordinal()] = forward - strafe - rotation;

        double max = Collections.max(Arrays.asList(1.0, Math.abs(motorPowers[0]),
                Math.abs(motorPowers[1]), Math.abs(motorPowers[2]), Math.abs(motorPowers[3])));
        if (max > 1.0) {
            for (int i = 0; i < 4; i++) {
                motorPowers[i] /= max;
            }
        }

        setPowers(motorPowers[LEFT_FRONT.ordinal()],
                motorPowers[LEFT_BACK.ordinal()],
                motorPowers[RIGHT_BACK.ordinal()],
                motorPowers[RIGHT_FRONT.ordinal()]);
    }
}
