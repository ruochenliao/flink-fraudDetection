package frauddetection;


import java.io.Serializable;
import java.util.Objects;

public class MyTransaction implements Serializable {
    private long accountId;
    private long timestamp;
    private double amount;

    public MyTransaction() {
    }

    public MyTransaction(long accountId, long timestamp, double amount) {
        this.accountId = accountId;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MyTransaction that = (MyTransaction)o;
            return this.accountId == that.getAccountId() && this.timestamp == that.getTimestamp() && Double.compare(that.getAmount(), this.amount) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.accountId, this.timestamp, this.amount});
    }

    @Override
    public String toString() {
        return "Transaction{accountId=" + this.accountId + ", timestamp=" + this.timestamp + ", amount=" + this.amount + '}';
    }
}
