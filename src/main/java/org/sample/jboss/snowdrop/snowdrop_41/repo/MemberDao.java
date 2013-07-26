package org.sample.jboss.snowdrop.snowdrop_41.repo;

import java.util.List;

import org.sample.jboss.snowdrop.snowdrop_41.domain.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
