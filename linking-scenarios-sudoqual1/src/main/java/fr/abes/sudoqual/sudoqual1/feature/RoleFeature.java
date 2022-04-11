package fr.abes.sudoqual.sudoqual1.feature;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.sudoqual1.util.adapter.AbstractSudoQual1Feature;

public class RoleFeature extends AbstractSudoQual1Feature<String> {

	private static final Logger logger = LoggerFactory.getLogger(RoleFeature.class);
	
	public RoleFeature() {
		super(false);
	}

	@Override
	public String getKey() {
		return "role";
	}

	@Override
	protected Object process(String roleLabel) {
		JSONObject json2Return = new JSONObject();
		json2Return.put("role",roleLabel);
		json2Return.put("group",group(roleLabel));
		return json2Return;
	}
	
	private static String group(String role)
	{
		switch(role) {
			case "actor": //fr:acteur
			case "choregrapher": //fr:chorégraphie
			case "dancer": //fr:danseur
			case "comedian": //fr:humoriste
			case "animator": //fr:animation
			case "commentator": //fr:commentaires
			case "author_of_dialogue": //fr:dialogues/commentaires
			case "production_designer": //fr:scénographe/chef_costumier
			case "recording_engineer": //fr:ingénieur_du_son
			case "production_personnel": //fr:accessoiriste
			case "director": //fr:réalisateur/metteur_en_scène/artistique
			case "film_editor": //fr:montage
			case "narrator": //fr:voix_parlée
			case "presenter": //fr:présentateur
			case "producer": //fr:directeur_de_production
			case "scenarist": //fr:scénario
				return "performing_arts";
			case "artist": //fr:artiste
			case "etcher": //fr:eaux-fortes
			case "calligrapher": //fr:calligraphie
			case "wood-engraver": //fr:gravure_sur_bois
			case "metal-engraver": //fr:gravure
			case "illustrator": //fr:illustrations/graphisme
			case "lithographer": //fr:lithographe
			case "sculptor": //fr:sculpteur
			case "graphics_technician": //fr:technicien_graphique
			case "engraver": //fr:gravure(1)
			case "cartographer": //fr:cartographie	
			case "photographer": //fr:photographie		
				return "graphic_arts";
			case "phD_student": //fr:doctorant
			case "opponent": //fr:membre_du_jury
			case "performer_of_research": //fr:centre_de_recherche
			case "research_team_head": //fr:directeur_de_recherches
			case "research_team_member": //fr:chercheur
			case "thesis_advisor":
			case "scientific_advisor":
			case "examiner":
			case "associate_laboratory":
			case "associate_company":
			case "associate_fondation":
			case "associate_research_team":
			case "other_associate_partner":
			case "joint_supervision_organisation":
			case "associate_ecole_doctorale":
			case "thesis_committee_president":
				return "research";
			case "bibliographic_antecedent": //fr:auteur_adapté/précédent
			case "commentator_for_written_text": //fr:notes
			case "compiler": //fr:compilation
			case "conceptor": //fr:idée_originale
			case "continuator": //fr:auteur(continuateur)
			case "dubious_author": //fr:auteur_supposé
			case "forger": //fr:faussaire
			case "interviewee": //fr:participant
			case "interviewer": //fr:intervieweur
			case "originator": //fr:créateur	
			case "programmer": //fr:programmeur
			case "project_manager": //fr:chef_de_projet
			case "reviewer": //fr:critique
			case "writer_of_accompanying_material": //fr:auteur_de_la_notice
			case "adapter": //fr:adaptation
			case "author": //fr:auteur
			case "lecturer": //fr:conférencier
			case "author_in_quotations_or_text_extracts": //fr:auteur_cité
			case "secretary": //fr:rédacteur
			case "author_of_introduction_etc.": //fr:préface
			case "author_of_afterword_postface_colophon_etc.": //fr:postface
			case "editor": //fr:éditeur_scientifique
			case "publishing_editor": //fr:directeur_de_la_publication
				return "unspecific_intellectual_contribution";
			case "corrector": //fr:correcteur(1)
			case "distributor": //fr:distributeur
			case "founder": //fr:fondateur
			case "translator": //fr:traduction
			case "sponsor": //fr:commanditaire
			case "assignee": //fr:nouveau_détenteur_du_privilège
			case "licensee": //fr:détenteur_du_privilège
			case "publisher": //fr:éditeur_commercial
			case "bookseller": //fr:libraire
			case "type_designer": //fr:dessin_des_caractères_d'imprimerie
			case "funder": //fr:mécène
				return "none";
			case "printer": //fr:imprimeur/imprimeur-libraire
			case "binder": //fr:relieur
			case "book_designer": //fr:maquette
			case "printer_of_plates": //fr:imprimeur_des_gravures
			case "compositor": //fr:compositeur_typographe
			case "typographer": //fr:typographe
			case "binding_designer": //fr:concepteur_de_la_reliure
			case "bookjacket_designer": //fr:conception_de_la_jaquette
				return "production"; 
			case "annotator": //fr:annotations
			case "illuminator": //fr:enluminures
			case "scribe": //fr:copiste
			case "bookplate_designer": //fr:dessin_de_l'ex-libris
			case "rubricator": //fr:rubricateur
			case "proof-reader": //fr:correcteur
				return "old_book";
			case "auctioneer": //fr:commissaire-priseur
			case "expert": //fr:expert
			case "curator_of_an_exhibition": //fr:commissaire
			case "vendor": //fr:vendeur
				return "art_market";
			case "standards_body": //fr:agence_de_normalisation				
			case "degree-grantor": //fr:organisme_de_soutenance
			case "inscriber": //fr:auteur_de_l'envoi
			case "patent_inventor": //fr:inventeur
			case "patentee": //fr:détenteur_du_brevet
			case "patent_applicant": //fr:demandeur(brevet)
			case "copyright_holder": //fr:copyright
			case "organiser_of_meeting": //fr:organisation_du_congrès
			case "licensor": //fr:concesseur
			case "dedicatee": //fr:dédicataire
			case "dedicator": //fr:auteur_de_la_dédicace
			case "recipient_of_letters": //fr:destinataire
			case "donor": //fr:donateur
			case "papermaker": //fr:fabricant_du_papier
			case "associated_name": //fr:nom_associé_à_l'exemplaire
			case "signer": //fr:signataire
			case "censor": //fr:censeur
			case "collector_of_field_material": //fr:collecteur
			case "issuing_body": //fr:collectivité_éditrice
			case "honoree": //fr:personne_honorée
			case "carried_out_by_role" : //fr:réalisée_par_rôle
			case "collaborator": //fr:collaborateur
			case "former_owner": //fr:ancien_possesseur
			case "consultant_to_a_project": //fr:consultant
			case "disc_jockey": //fr:DJ
			case "composer_of_adapted_work": //fr:compositeur_de_l'œuvre_adaptée
			case "composer_of_main_musical_work": //fr:compositeur_de_l'œuvre_musicale_principale
			case "impresario": //fr:impresario(production_musicale_et_théâtrale)
			case "other": //fr:fonction_non_precisée
			case "record_producer": //fr:producteur_d'enregistrement
			case "remixer": //fr:remixer
			case "monitor": //fr:contrôleur
			case "puppeteer": //fr:marionnettiste			
			case "mime_artist": //fr:mime	
			case "stunt_performer": //fr:cascadeur
			case "circus_performer": //fr:artiste_de_cirque
				return "neutral";
			case "composer": //fr:musique
			case "musician": //fr:instrumentiste
			case "singer": //fr:chant(1)
			case "conductor": //fr:chef_d'orchestre
			case "performer": //fr:exécutant
			case "librettist": //fr:livret
			case "arranger": //fr:arrangement/réduction
			case "choral_director": //fr:chef_de_choeur
			case "lyricist": //fr:paroles
			case "vocalist": //fr:chant
				return "music";
			default :
				if(logger.isWarnEnabled()) {
					logger.warn("unknow role : {}", role);
				}
				return null;	
		}
	}

}
