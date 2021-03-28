package game;

import city.cs.engine.*;

public class RedBallCharacter extends DynamicBody {
    //the following code provides us with a red ball image and the appropriate shape that matches the image
    private static final Shape shape = new CircleShape(1);
    private static final BodyImage image = new BodyImage("data/redBall.png",2.0f);
    //

    public RedBallCharacter(World w) {
        super(w, shape);
        addImage(image);

    }
}
