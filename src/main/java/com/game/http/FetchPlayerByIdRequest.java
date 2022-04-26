package com.game.http;

public class FetchPlayerByIdRequest {
    private Long id;

    public FetchPlayerByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FetchPlayerByIdRequest)) return false;
        final FetchPlayerByIdRequest other = (FetchPlayerByIdRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FetchPlayerByIdRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "FetchPlayerByIdRequest(id=" + this.getId() + ")";
    }
}
