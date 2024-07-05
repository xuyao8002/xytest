package com.xuyao.test.pattern.state;

abstract class State {
    protected DollMachine machine;

    public void setMachine(DollMachine machine) {
        this.machine = machine;
    }

    public abstract void insertCoin();
    public abstract void pressButton();
    public abstract void grabDoll();
}

// 具体状态类 - 没有投币状态
class NoCoinState extends State {
    @Override
    public void insertCoin() {
        System.out.println("投入一个硬币");
        machine.setCoinCount(machine.getCoinCount() + 1);
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public void pressButton() {
        System.out.println("请先投入一个硬币");
    }

    @Override
    public void grabDoll() {
        System.out.println("请先按按钮");
    }
}

// 具体状态类 - 已投币状态
class HasCoinState extends State {
    @Override
    public void insertCoin() {
        System.out.println("已投入一个硬币");
    }

    @Override
    public void pressButton() {
        System.out.println("按下按钮");
        machine.setCoinCount(machine.getCoinCount() - 1);
        machine.setState(machine.getGrabbingState());
    }

    @Override
    public void grabDoll() {
        System.out.println("请先按按钮");
    }
}

// 具体状态类 - 没有娃娃状态
class NoDollState extends State {
    @Override
    public void insertCoin() {
        System.out.println("没有娃娃了");
    }

    @Override
    public void pressButton() {
        System.out.println("请先投入一个硬币");
    }

    @Override
    public void grabDoll() {
        System.out.println("请先按按钮");
    }
}

// 具体状态类 - 抓取娃娃状态
class GrabbingState extends State {
    @Override
    public void insertCoin() {
        System.out.println("正在抓娃娃，请等待");
    }

    @Override
    public void pressButton() {
        System.out.println("正在抓娃娃，请等待");
    }

    @Override
    public void grabDoll() {
        machine.releaseDoll();
        if(machine.getDollCount() > 0){
            machine.setState(machine.getNoCoinState());
        }else{
            machine.setState(machine.getNoDollState());
        }
    }
}

// 娃娃机类
class DollMachine {
    private State noCoinState;
    private State hasCoinState;
    private State grabbingState;
    private State noDollState;

    private State state;
    private int dollCount;
    private int coinCount;

    public DollMachine(int dollCount) {
        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        grabbingState = new GrabbingState();
        noDollState = new NoDollState();

        state = noCoinState;
        this.dollCount = dollCount;
        coinCount = 0;

        noCoinState.setMachine(this);
        hasCoinState.setMachine(this);
        grabbingState.setMachine(this);
        noDollState.setMachine(this);
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getGrabbingState() {
        return grabbingState;
    }

    public State getNoDollState() {
        return noDollState;
    }

    public int getDollCount() {
        return dollCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void insertCoin() {
        state.insertCoin();
    }

    public void pressButton() {
        state.pressButton();
    }

    public void grabDoll() {
        state.grabDoll();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseDoll() {
        if (dollCount > 0) {
            dollCount--;
            System.out.println("一个娃娃被释放");
        }
    }

}


public class StatePattern {
    public static void main(String[] args) {
        DollMachine machine = new DollMachine(2);

        machine.insertCoin();
        machine.pressButton();
        machine.grabDoll();

        machine.insertCoin();
        machine.pressButton();
        machine.grabDoll();

        machine.insertCoin();
        machine.pressButton();
        machine.grabDoll();
    }
}