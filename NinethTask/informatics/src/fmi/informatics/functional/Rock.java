package fmi.informatics.functional;

// Демонстриране чрез клас имплементиращ интерфейса IGravity
public class Rock implements IGravity {

	public void drop(double height) {
		double heightMeters = height(height);
		double timeToHitGround = Math.sqrt(2 * heightMeters / acceleration()); 
		System.out.format("It took %f seconds for the rock to hit the ground", timeToHitGround);

		double speedAtImpact = doubleSpeedAtImpact(heightMeters);

		System.out.println("The speed of impact is " + speedAtImpact + ".");
	}

	@Override
	public double height(double height) {
		return Math.abs(height);
	}
}
