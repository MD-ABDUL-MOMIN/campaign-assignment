package com.example.testapp.campaign_package;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.testapp.campaign_group_package.CampaignGroupModel;

@Controller
public class CampaignController {
	
	@Autowired
	private CampaignService campaignService;
	
	@GetMapping("/campaign")
	public String showCampaignList(Model model) {
		
		List<CampaignModel> campaigns = new ArrayList<CampaignModel>();
		campaigns = campaignService.findCampaignList();
		model.addAttribute("campaigns", campaigns);
		
		return "pages/campaign/campaign_list";
	}
	
	
	
	
	/*get and post method for campaign create  m*/

	
	@GetMapping("/campaign/add")
	public String addCampaign(Model model) {
		
		CampaignModel camModel = new CampaignModel();
		model.addAttribute("campaignModel", camModel);
		List<CampaignGroupModel> campaignGroupList = new ArrayList<CampaignGroupModel>();
		campaignGroupList = campaignService.getCampaignGroups();
		
		model.addAttribute("campaignGroups", campaignGroupList);
	

		return "pages/campaign/campaign_adder";
	}
	
	
	
	@PostMapping("/campaign/add")
	public String saveCampaign(@ModelAttribute CampaignModel campaignModel,@ModelAttribute  CampaignGroupModel campaignGroupModel) {
				
		campaignModel.setCampaignGroupModel(campaignGroupModel);
		
		campaignService.saveCampaign(campaignModel);

		return "redirect:/campaign";
	}
	
	
	///Campaign group edit Getmapping and postmapping
	
		@GetMapping("/campaign/edit")
		public String modifyCampaignById(@RequestParam("id") long id, Model model) {
			
			List<CampaignGroupModel> campaignGroupList = new ArrayList<CampaignGroupModel>();
			campaignGroupList = campaignService.getCampaignGroups();
			model.addAttribute("campaignGroups", campaignGroupList);
			
			CampaignModel camModel = campaignService.findCampaignById(id);
			
			model.addAttribute("editableId",id);
			model.addAttribute("campaignModel", camModel);
			
			return "pages/campaign/edit_campaign";
			
		}

		@PostMapping("/campaign/edit/{id}")
		public String updateCampaignById(@PathVariable long id,CampaignModel campaignModel) {
	   
			campaignService.updateCampaignGroup(campaignModel);
			return "redirect:/campaigngroup";

		}

	


}
