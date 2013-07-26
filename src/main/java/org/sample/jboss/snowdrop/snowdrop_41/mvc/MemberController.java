package org.sample.jboss.snowdrop.snowdrop_41.mvc;

 import javax.ejb.EJB;
import javax.validation.Valid;

import org.sample.jboss.snowdrop.snowdrop_41.domain.Member;
import org.sample.jboss.snowdrop.snowdrop_41.ejb.InjectEJB;
import org.sample.jboss.snowdrop.snowdrop_41.repo.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class MemberController
{
	@EJB(mappedName="java:app/snowdrop-41/InjectEJBImpl")
	private InjectEJB injectEJBImpl;
	
    @Autowired
    private MemberDao memberDao;

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result, Model model)
    {
    	injectEJBImpl.writeSimplePersonData();
        if (!result.hasErrors()) {
            memberDao.register(newMember);
            return "redirect:/";
        }
        else {
            model.addAttribute("members", memberDao.findAllOrderedByName());
            return "index";
        }
    }
}
