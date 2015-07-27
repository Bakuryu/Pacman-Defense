/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Components.Collider;
import Math.Point2D;
import Math.Vector2D;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Bakuryu
 */
public class BulletEntity extends Entity
{

    private String type;
    private double speed;
    private Texture bullSpr;
    private Point2D target;
    private Vector2D targetVector;
    private Collider hitBox;

    private AgentEntity targetEntity;
    private TowerEntity bulletOwner;

    public BulletEntity(TowerEntity t, AgentEntity a)
    {
        bulletOwner = t;
        this.type = t.getTType();
        position = new Point2D(t.getTCenterPoint());
        target = a.getCenterPos();
        targetEntity = a;
        createBullet();
    }

    @Override
    public void update(float t)
    {

        if (!hitBox.checkEntityCollision(hitBox, targetEntity.getCollider()))
        {
            targetVector = target.minus(position);
            targetVector.normalize();
            targetVector.timesEquals(speed * t / 1000);
            position.plusEquals(targetVector);
        }
        else
        {
            if (type == "reg")
            {
                bulletOwner.targetHit(this);
                targetEntity.takeDmg(1);
            }
            else
            {
                bulletOwner.targetHit(this);
                targetEntity.isScared(true);
            }
        }

//        sDraw.drawRect(hitBox.getHitBox().x,hitBox.getHitBox().y,hitBox.getHitBox().width,hitBox.getHitBox().height,2,Color.WHITE);
    }

    private void createBullet()
    {
        if (type == "reg")
        {
            bullSpr = new Texture("graphics/RegBullet.png");

            hitBox = new Collider(position, 8, 8);
            speed = 150;

        }

        if (type == "sup")
        {
            bullSpr = new Texture("graphics/pellBullet.png");

            hitBox = new Collider(position, 16, 16);
            speed = 120;

        }
    }

    public Texture getBSprite()
    {
        return bullSpr;
    }

    public Collider getCollider()
    {
        return hitBox;
    }

}
